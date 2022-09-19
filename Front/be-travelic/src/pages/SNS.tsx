import { Feed } from "../components/index";
import { UserRecommend } from "../components/index"

function SNS() {
  return (
    <div>
      <div className="container flex">
        <Feed />
        <UserRecommend />
      </div>
    </div>
  )
}

export default SNS;