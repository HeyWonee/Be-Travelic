import React, { SetStateAction } from "react";
import { useRef } from "react";
import CloseButton from "./CloseButton";
import '../css/PhotoInputModal.css'

// 허용가능한 확장자 목록!
const ALLOW_FILE_EXTENSION = "jpg,jpeg,png";
const FILE_SIZE_MAX_LIMIT = 5 * 1024 * 1024; // 5MB

const PhotoInputModal: React.FC<{
  setShowModal: React.Dispatch<React.SetStateAction<boolean>>;
}> = (props) => {
  const imageInput = useRef<HTMLInputElement>(null);
  const uploadImageHandler = () => {
    imageInput.current?.click();
  };

  const changePhotoHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    console.log(event.target.files![0]);
  };

  const modalCloseHandler = () => {
    props.setShowModal(false);
  };

  return (
    <div className="backdrop">
      <div className="modalContainer">
        <input
          type="file"
          style={{ display: "none" }}
          ref={imageInput}
          onChange={changePhotoHandler}
        />
        <div className="titleContainer">
          <h2 className="title">추억을 기록하세요</h2>
          <div className="closeBtnContainer" onClick={modalCloseHandler}>
            <CloseButton />
          </div>
        </div>
        <div className="btnContainer">
          <button
            className="bg-blue-400 mx-4 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            onClick={uploadImageHandler}
          >
            사진 등록
          </button>
          <button className="bg-red-100 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            사진 삭제
          </button>
        </div>
      </div>
    </div>
  );
};

export default PhotoInputModal;
