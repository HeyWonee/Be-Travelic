import "../css/PlaceDetail.css"

interface DetailInfo {
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

function DetailInfo( props: DetailInfo) {

  const { placeId, addr, title, image, score, overview } = props  

  return (
    <section className="text-gray-600 body-font overflow-hidden">
      <div className="container px-5 py-24 mx-auto">
        <div id="placeDetailInfoCard">
          <div id="placeDetailInfo">
            <div className="lg:w-full mx-auto flex flex-wrap lg:pl-2">
              <img
                id="placeImage"
                alt="placeImage"
                className="lg:w-1/2 w-full lg:h-auto h-64 object-cover object-center rounded"
                src={image}
              />
              <div className="lg:w-1/2 w-full lg:pl-10 lg:pr-2 lg:py-6 mt-6 lg:mt-0">
                <div className="flex">
                  <h1 className="text-gray-900 text-3xl title-font font-medium mb-1">
                    {title}
                  </h1>

                  {/* 북마크 버튼 */}
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
                <h3 className="text-gray-900 text-xl title-font font-medium m-1 m">{addr}</h3>

                {/* 별점과 리뷰 */}
                <div className="flex m-1">
                  {(function () {
                    let stars = [];
                    for (let i = 0; i < score; i++) {
                      stars.push(<span>⭐</span>);
                    }
                    return stars;
                  })()}
                  <p>({score})</p>
                </div>

                <p className="leading-relaxed text-lg m-1 mt-5">{overview}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default DetailInfo