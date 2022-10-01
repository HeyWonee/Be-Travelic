import { useEffect, useState } from "react";
import axios from 'axios'

import DetailRecommendItem from "./DetailRecommendItem";
import "../css/PlaceDetail.css"

interface DetailRecommend {
  title: string;
}


  useEffect(() => {
    axios
      .get(`http://j7d205.p.ssafy.io:8081/api/v1/another_recommend/${title}`)
      .then(( { data }) => {
        console.log(data)
        setRecommendPlace(data)
      })
      .catch((err) => console.log(err))
  }, [])

  return (
    <div className="carouselWrapper">
      <div
          className="carousel"
          style={{ transform: `translateX(${move}px)` }}
        >
          { recommendplaces.map((place, index) => (
            <DetailRecommendItem
            key={index}
            place_id={place.place_id}
            addr={place.addr}
            score={place.score}
            mapx={place.mapx}
            mapy={place.mapy}
            title={place.title}
            image={place.image}
            overview={place.overview}
            />
          ))
          }
      </div>
    </div>
  )
}

export default DetailRecommend

