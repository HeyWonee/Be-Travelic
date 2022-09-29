import React, { useState } from "react";
import { Follow } from "../../apis/mypage";
import CloseButton from "../MyPage/CloseButton";

const FollowModal: React.FC<{
  setShowFollowModal: React.Dispatch<React.SetStateAction<boolean>>;
  tabNumber: number;
  follows: Follow[];
  setFollows: React.Dispatch<React.SetStateAction<Follow[]>>;
  userId:string
}> = ({ setShowFollowModal, tabNumber, follows, setFollows, userId }) => {
  const closeFollowModalHandler = () => {
    setShowFollowModal(false);
  };

  const [openTab, setOpenTab] = useState(tabNumber);

  const fetchFollows = async (
    identifier: string,
    e: React.MouseEvent<HTMLElement>
  ) => {
    e.preventDefault();
    const tabNum = identifier === "followerList" ? 1 : 2;
    // const res = await fetchFollows(userId!, identifier);
    // setFollows(res.data);

    setOpenTab(tabNum);
  };

  return (
    <div className="backdrop">
      <div className="modalContainer">
        <div className="flex flex-wrap">
          <div className="w-full">
            <ul
              className="flex mb-0 list-none flex-wrap pt-3 pb-4 flex-row"
              role="tablist"
            >
              <li className="-mb-px mr-2 last:mr-0 flex-auto text-center">
                <a
                  className={
                    "text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal " +
                    (openTab === 1
                      ? "text-white bg-" + "blue" + "-400"
                      : "bg-white")
                  }
                  onClick={fetchFollows.bind(this, "followerList")}
                  data-toggle="tab"
                  href="#link1"
                  role="tablist"
                >
                  팔로워
                  {/* map으로 랜더링 */}
                </a>
              </li>
              <li className="-mb-px mr-2 last:mr-0 flex-auto text-center">
                <a
                  className={
                    "text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal " +
                    (openTab === 2
                      ? "text-white bg-" + "blue" + "-400"
                      : "bg-white")
                  }
                  onClick={fetchFollows.bind(this, "followingList")}
                  data-toggle="tab"
                  href="#link2"
                  role="tablist"
                >
                  팔로잉
                  {/* map으로 랜더링 */}
                </a>
              </li>
            </ul>
            <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
              <div className="px-4 py-5 flex-auto">
                <div className="tab-content tab-space">
                  <div
                    className={openTab === 1 ? "block" : "hidden"}
                    id="link1"
                  ></div>
                  <div
                    className={openTab === 2 ? "block" : "hidden"}
                    id="link2"
                  ></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="flex justify-end" onClick={closeFollowModalHandler}>
          <CloseButton />
        </div>
      </div>
    </div>
  );
};

export default FollowModal;
