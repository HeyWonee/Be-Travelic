function UserRecommend() {
    return(
            <div className="p-4 xl:w-1/4 md:w-1/2">
                <div className="h-full p-6 rounded-lg border-2 border-gray-300 flex flex-col relative overflow-hidden">
                    <h2 className="text-ml text-gray-900 pb-4 mb-4 border-b border-gray-200 leading-none">다른 사용자 추천</h2>
                    <p className="flex items-center text-gray-600 mb-2">
                        <span className="w-4 h-4 mr-2 inline-flex items-center justify-center bg-gray-400 text-white rounded-full flex-shrink-0">
                            <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" className="w-3 h-3" viewBox="0 0 24 24">
                                <path d="M20 6L9 17l-5-5"></path>
                            </svg>
                        </span>

                        <a className="inline-flex items-center">
                            <img alt="blog" src="https://dummyimage.com/103x103" className="w-8 h-8 rounded-full flex-shrink-0 object-cover object-center" />
                            <span className="flex-grow flex flex-col pl-3">
                                <span className="title-font font-medium text-gray-900">Alper Kamu</span>
                            </span>
                        </a>

                        <button className="flex ml-auto bg-indigo-500 border-0 py-2 px-6 focus:outline-none rounded">팔로우</button>
                    </p>
                </div>
            </div>
    )
}

export default UserRecommend;