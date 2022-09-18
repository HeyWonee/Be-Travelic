import "../css/SNS.css"
import { FaMapMarkerAlt, FaRegCalendarAlt } from 'react-icons/fa'

// interface Feed {
//   id: number;
//   nickname: string;
//   date: Date;
//   place: string;
//   imgUrl: string;
//   likes: number;
// }

function Feed() {
  return (
    <div id="FeedCard">
      <div id="FeedCardHeader" className="flex items-center p-4">
        <img
          alt="team" className="w-16 h-16 bg-gray-100 object-cover object-center flex-shrink-0 rounded-full mr-4"
          src="https://dummyimage.com/80x80"
        />
        <div className="flex-grow">
          <h2 className="title-font font-medium">Holden Caulfield</h2>
          {/* 위치 여기다 넣을지 고민중 */}
          {/* <p className="text-gray-500">UI Designer</p> */}
        </div>

        <FaMapMarkerAlt id="MarkIcon"/>        
        <h2 className="ml-1 mr-5">포항 영일대</h2>

        <FaRegCalendarAlt id="CalendarIcon" />
        <h2 className="ml-1 mr-3">2022-09-19</h2>
      </div>

      <div id="FeedCardBody">
        <img
          id="FeedImage"
          className="lg:w-2/6 md:w-3/6 w-5/6 mb-10 object-cover object-center rounded"
          alt="SNSimage"
          src=""
        />

        {/* 좋아요 버튼 => 수정 예정 */}
        <div className="flex">
          <button className="rounded-full w-10 h-10 bg-gray-200 p-0 border-0 inline-flex items-center justify-center text-gray-500">
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
          <p className="mt-2">52</p>

          {/* 댓글버튼 */}
          <button className="rounded-full ml-3 w-10 h-10 bg-gray-200 p-0 border-0 inline-flex items-center justify-center text-gray-500">
            <svg fill="none" stroke="grey" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" className="w-5 h-5" viewBox="0 0 24 24">
              <path d="M21 11.5a8.38 8.38 0 01-.9 3.8 8.5 8.5 0 01-7.6 4.7 8.38 8.38 0 01-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 01-.9-3.8 8.5 8.5 0 014.7-7.6 8.38 8.38 0 013.8-.9h.5a8.48 8.48 0 018 8v.5z"></path>
            </svg>
          </button>
          <p className="mt-2">8</p>
        </div>

        <div className="lg:w-2/3 w-full">
          <p className="ml-3 mb-8 leading-relaxed">
            Meggings kinfolk echo park stumptown DIY, kale chips beard jianbing
            tousled. Chambray dreamcatcher trust fund, kitsch vice godard disrupt
            ramps hexagon mustache umami snackwave tilde chillwave ugh. Pour-over
            meditation PBR&amp;B pickled ennui celiac mlkshk freegan photo booth
            af fingerstache pitchfork.
          </p>
        </div>
      </div>
    </div>
  );
}

export default Feed;