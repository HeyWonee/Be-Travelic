import React from 'react';
import { useState  } from 'react';
import '../css/CommentsModal.css';

interface props {
  open: boolean;
  close: boolean;
}

const CommentsModal = (props: any) => {
  // 모달 => 열기, 닫기를 부모로부터 받아옴
  const { open, close } = props;

  // 댓글
  const [review, setReview] = useState('졸려');
  const [reviewArray, setReviewArray] = useState([
    { id: `수영`, review: review },
  ]);

  const handleReviewInput = (event: any) => {
    setReview(event.target.value);
  };
  const handleTotalEnter = (event: any) => {
    if (event.key === 'Enter') {
      event.preventDefault();
      const repoArray = [...reviewArray];
      if (event.target.value !== '')
        repoArray.push({ id: '익명', review: review });
      setReviewArray(repoArray);
      event.target.value = '';
    }
  };

  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <div id="CommentsModal" className={open ? 'openModal modal' : 'modal'}>
      {open ? (
        <section>
          <header>
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <main>{props.children}</main>
          {reviewArray.map(data => (
                    <li key={data.id}>
                      <span >{data.id}</span>
                      <span className="text"> {data.review}</span>
                    </li>
                  ))}
          <input
                  className="comment-input"
                  type="text"
                  placeholder="댓글를 입력해주세요."
                  onKeyPress={event => {
                    handleTotalEnter(event);
                  }}
                  onKeyUp={event => {
                    handleReviewInput(event);
                  }}/>
          <footer>
            <button className="close" onClick={close}>
              close
            </button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};


export default CommentsModal;