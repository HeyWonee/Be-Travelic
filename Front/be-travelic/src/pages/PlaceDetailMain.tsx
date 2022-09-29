import { useEffect, useState } from "react";
import axios from "axios";

import { DetailInfo, DetailRecommend, MapContainer } from "../components/index";
import "./css/PlaceDetailMain.css";

interface Place {
  plaec: string;
  placeId: number;
  categories: number;
  region: number;
  addr: string;
  title: string;
  image: string;
  mapx: string;
  mapy: string;
  score: number;
  content_id: number;
  overview: string;
}

const RecommendData = [
  {
  title: '남산타워',
  imgUrl: '',
  },
  {
    title: '불국사',
    imgUrl: '',
  },
  {
    title: '롯데월드',
    imgUrl: '',
  },
  {
    title: '해인사',
    imgUrl: '',
  }
];

function PlaceDetailMain() {
  const place_id = 1
  const [ place, setPlace ] = useState<Place[]>([]);

  // 여행지 상세정보 GET (spring)
  useEffect(() => {
    axios
      .get(`http://j7d205.p.ssafy.io:8443/places/${place_id}`)
      .then(( { data } ) => {
        console.log(data)
        setPlace(data)
      })
      .catch((err) => console.log(err))
  }, [])


  return (
    <div>
      <div>
      {/* 여행지 상세정보 */}
      <section className="text-gray-600 body-font overflow-hidden">
      <div className="container px-5 py-24 mx-auto">
        <div id="placeDetailInfoCard">
          <div id="placeDetailInfo">
            <div className="lg:w-4/5 mx-auto flex flex-wrap">
              <img
                id="placeImage"
                alt="placeImage"
                className="lg:w-1/2 w-full lg:h-auto h-64 object-cover object-center rounded"
                src={place.image}
              />

              <div className="lg:w-1/2 w-full lg:pl-10 lg:py-6 mt-6 lg:mt-0">
                {/* <h2 className="text-sm title-font text-gray-500 tracking-widest">{category}</h2> */}
                <div className="flex">
                  <h1 className="text-gray-900 text-3xl title-font font-medium mb-1">
                    {title}
                  </h1>

                  {/* 북마크 버튼 => 수정 예정 */}
                  <button className="rounded-full w-10 h-10 bg-gray-200 p-0 border-0 inline-flex items-center justify-center text-gray-500 ml-4">
                    <svg
                      fill="red"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      className="w-5 h-5"
                      viewBox="0 0 24 24"
                    >
                      <path d="M20.84 4.61a5.5 5.5 0 00-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 00-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 000-7.78z"></path>
                    </svg>
                  </button>
                </div>

                <hr />

                <h3 className="m-1">{addr}</h3>
                {/* 전화번호 <h3 className="m-2">{phoneNumber}</h3> */}

                {/* 별점과 리뷰 => 수정 및 추가 예정(개수) */}
                <div className="flex m-1">
                  {(function () {
                    let stars = [];
                    for (let i = 0; i < score; i++) {
                      stars.push(<span>⭐</span>);
                    }
                    return stars;
                  })()}
                  <p>(50)</p>
                </div>

                <p className="leading-relaxed m-1 mt-3">{overview}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
      </div>

      <div className="container px-5 mx-auto">
        <div id="kakaomap">
          <MapContainer
            lat={mapx}
            lng={mapy}
          />
        </div>
      </div>
      
      {/* 추천여행지 카드 */}
      <section className="text-gray-600 body-font">
        <div className="container px-5 py-24 mx-auto">
          <div className="flex flex-wrap w-full mb-10">
            <div className="w-full mb-6 lg:mb-0">
              <h1 className="sm:text-3xl text-2xl text-center font-medium title-font mb-4 text-gray-900">다른 여행지는 어때요? 😎</h1>
              <hr/>
            </div>
          </div>

          <div className="flex flex-wrap" id="DetailRecommendContainer">
            {RecommendData.map((place) => (
                <DetailRecommend
                title={place.title}
                imgUrl={place.imgUrl}
                />
              ))}
          </div>

        </div>
      </section>
    </div>
  )
}

export default PlaceDetailMain