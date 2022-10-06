import axios, { AxiosError } from "axios";
import { getMemberId } from "./auth";
import { djangoAxios, springAxios } from "./index";

export interface Follow {
  follower_id?: string;
  following_id?: string;
  nickname: string;
}

export interface Region {
  userId?: string;
  regionId?: string;
}

export interface userInfoType {
  followerCnt?: number;
  followingCnt?: number;
  travelRecords?: number;
  reviewCnt?: number;
  surveyKeyword?: string[];
  user_id: number;
}

export const fetchFollowList = async (followType: string, user_id: number) => {
  const url = `/follow/${followType}`;

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });

    console.log(res, "팔로우리스트");

    return res.data;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
  }
};

export const fetchAllVisitedPlaces = async (user_id: number) => {
  const url = `/mypage/travel-history/user`;

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });

    return res.data;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
  }
};

export const fetchRegionalVisitedPlaces = async (
  regionId: string,
  user_id: number
) => {
  const url = `/mypage/travel-history/region/${regionId}/user`;

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });
    return res.data;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
  }
};

export const fetchAllBookMarks = async (user_id: number) => {
  const url = `/bookmark/user`;

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });

    return res.data;
  } catch (error) {}
};

export const fetchRegionalBookMarks = async (
  regionId: string,
  user_id: number
) => {
  const url = `/bookmark/region/${regionId}/user`;

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });

    return res.data;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
  }
};

export const fetchUserInfo = async (user_id: number) => {
  const url = "users";

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });
    console.log("유저인포", res.data.data);

    return res.data.data;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
    console.log(err);
  }
};

export const fetchMapPhoto = async (data: File, regionId: string) => {
  const url = "/mypage/uploadMyPicture";
  const formData = new FormData();
  formData.append("file", data);

  console.log(formData);

  try {
    const res = await springAxios({
      method: "post",
      url,
      headers: {
        "Content-type": "multipart/form-data",
      },
      data: formData,
      params: {
        region_id: regionId,
      },
    });

    console.log(res);
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
    console.log(err);
  }
};

export const getMapPothos = async () => {
  const url = "/mypage/downloadMyPicture";
  const user_id = await getMemberId();

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });
    console.log(res.data, "getmap");

    return res.data;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
    console.log(err);
  }
};

export const fetchPorfilePhoto = async (data: File) => {
  const url = "users/profile/upload";
  const formData = new FormData();
  formData.append("file", data);

  console.log(formData);

  try {
    const res = await springAxios({
      method: "post",
      url,
      headers: {
        "Content-type": "multipart/form-data",
      },
      data: formData,
    });

    console.log(res);
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
    console.log(err);
  }
};

export const downloadProfilePhoto = async () => {
  const url = "users/profile/download";
  const user_id = await getMemberId();

  try {
    const res = await springAxios({
      method: "get",
      url,
      params: { user_id },
    });
    console.log(res);

    return res;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
    console.log(err);
  }
};

export const fetchFollow = async (follower_id: string) => {
  const url = "follow";

  try {
    const res = await springAxios({
      method: "post",
      url,
      params: {
        follower_id,
      },
    });

    console.log(res);
  } catch (error) {
    const err = error as AxiosError;
    console.log(err.response?.data);
    console.log(err);
  }
};
