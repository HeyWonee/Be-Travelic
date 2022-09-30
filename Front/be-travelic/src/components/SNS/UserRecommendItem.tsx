import "../css/UserRecommend.css"
import { Link } from "react-router-dom";

interface UserRecommend {
    image: string;
    nickname: string;
    user_id: number;
}

function UserRecommendItem( props: UserRecommend ) {
    const { user_id, image, nickname } = props
    return(
      <div id="UserRecommendCardItem">
          <p className="flex items-center m-4">
              <a className="inline-flex items-center">
                  <img alt="ProfileImage" src={image} className="w-8 h-8 rounded-full object-cover object-center" />
                      <Link to={`/mypage/{user_id}`}>
                          <span className="flex-grow flex flex-col pl-3">
                              <span className="title-font font-medium text-gray-900">{nickname}</span>
                          </span>
                      </Link>
              </a>
              
              {/* 팔로우 버튼 => 수정예정 */}
              <button id="FollowButton" className="flex ml-auto bg-indigo-500 border-0 py-1 px-2 focus:outline-none rounded">팔로우</button>
          </p>
      </div>
    )
}

export default UserRecommendItem;