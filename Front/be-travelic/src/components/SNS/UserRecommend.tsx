import UserRecommendItem from "./UserRecommendItem"
import "../css/SNS.css"

const UserRecommendData = [
    {
      userid: 1,
      nickname: '혜원'
    },
    {
      userid: 2,
      nickname: '지명'
    },
    {
      userid: 3,
      nickname: '호형'
    },
    {
      userid: 4,
      nickname: '수영'
    },
    {
      userid: 5,
      nickname: '윤해'
    },
    {
      userid: 6,
      nickname: '채현'
    }
  ]


function UserRecommend() {
    return (
        <div id="UserRecommend" className="p-4 mt-20 mr-10">
          <div id="UserRecommendCard" className="flex flex-col">
              <div id="UserRecommendCardHeader">
                  <h2 className="text-ml m-5 text-center text-gray-900">다른 사용자 추천 🚀</h2>
              </div>
              {UserRecommendData.map((user) => (
              <div key='{user.userid}'>
                  <UserRecommendItem
                      userid={user.userid}
                      nickname={user.nickname}
                  />
              </div>
              ))}
          </div>                    
        </div>
    )
}

export default UserRecommend