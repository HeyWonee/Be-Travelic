import React, { useEffect, useLayoutEffect, useRef, useState } from "react";
import "../css/UploadPhoto.css";
import logo from "../../assets/image/logo.png";
import {
  downloadProfilePhoto,
  fetchFollow,
  fetchIsFollowed,
  fetchProfilePhoto,
  userInfoType,
} from "../../apis/mypage";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "../../store";
const AVATAR =
  "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";

const UploadPhoto: React.FC<{
  type: string;
  userId: number;
  setUserInfo: React.Dispatch<React.SetStateAction<userInfoType>>;
  followerCnt: number;
}> = ({ type, userId, setUserInfo, followerCnt }) => {
  const [image, setImage] = useState<string>(() => {
    if (type === "place") {
      return logo;
    }
    return AVATAR;
  });
  const currentMember = useSelector((state: RootState) => state.auth.userId);
  const [isFollowed, setIsFollowed] = useState(false);
  const [file, setFile] = useState<File>();

  const imageInput = useRef<HTMLInputElement>(null);

  const fetchFollowHandler = async () => {
    const res = await fetchFollow(userId, isFollowed);
    if (res?.status === 200) {
      setIsFollowed((prev) => !prev);
      const calc = isFollowed ? -1 : 1;
      console.log("변경");

      setUserInfo((prev) => {
        return {
          ...prev,
          followerCnt: followerCnt + calc,
        };
      });
    }
  };

  const changePhotoHandler = async (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const identifier = image === AVATAR ? "post" : "put";
    if (event.target.files![0]) {
      setFile(event.target.files![0]);
      const res = await fetchProfilePhoto(event.target.files![0], identifier);
    } else {
      //업로드 취소할 시
      if (type === "place") {
        setImage(logo);
        return;
      } else if (type === "avatar") {
        setImage(AVATAR);
        return;
      }
    }

    const reader = new FileReader();
    reader.onload = () => {
      if (reader.readyState === 2 && typeof reader.result === "string") {
        setImage(reader.result);
      }
    };
    reader.readAsDataURL(event.target.files![0]);
  };

  const uploadImageHandler = () => {
    if (userId !== Number(currentMember)) {
      return;
    }

    imageInput.current?.click();
  };

  useLayoutEffect(() => {
    const initialData = async () => {
      // follow 여부 추가
      const [image, follow] = await Promise.all([
        downloadProfilePhoto(userId),
        fetchIsFollowed(userId),
      ]);
      if (image) {
        setImage(image.data);
      }
      console.log(follow);

      if (follow) {
        setIsFollowed(follow);
      }
    };
    initialData();
  }, []);

  return (
    <>
      {type === "place" && image === logo && (
        <label htmlFor="upload-image">사진 업로드</label>
      )}
      <input
        id="upload-image"
        type="file"
        style={{ display: "none" }}
        accept="image/jpg,impge/png,image/jpeg"
        name="profile_img"
        onChange={changePhotoHandler}
        ref={imageInput}
      />
      <div className="flex relative justify-center pt-5">
        {/* jwt !== 해당 페이지 유저 */}
        {userId !== Number(currentMember) && (
          <button
            id="FollowButton"
            className="flex absolute right-3 ml-auto bg-indigo-500 border-0 py-1 px-2 focus:outline-none rounded"
            onClick={fetchFollowHandler}
          >
            {isFollowed ? "언팔로우" : "팔로우"}
          </button>
        )}
        <img
          src={image}
          alt="avatar"
          className={`${type === "avatar" ? "avatar" : "place"}`}
          onClick={uploadImageHandler}
        />
      </div>
    </>
  );
};

export default UploadPhoto;
