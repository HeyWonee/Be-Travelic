from konlpy.tag import Okt
from numpy import dot
from numpy.linalg import norm
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.decomposition import TruncatedSVD
import pandas as pd
import numpy as np
import pymysql
# from eunjeon import Mecab
from konlpy.tag import Okt
from collections import Counter

conn = pymysql.connect(host='j7d205.p.ssafy.io',
                        user='root',
                        password='betravelic205',
                        db='D205_2',
                        charset='utf8')



follow_table = "SELECT * FROM follow"
user_table = "SELECT * FROM user"
place_table = "SELECT * FROM place"
category_table = "SELECT * FROM categories"
review_table = "SELECT * FROM review"
keyword_table = "SELECT * FROM survey_keyword"

follow_data = pd.read_sql_query(follow_table, conn)
user_data = pd.read_sql_query(user_table, conn)
place_data = pd.read_sql_query(place_table, conn)
category_data = pd.read_sql_query(category_table, conn)
review_data = pd.read_sql_query(review_table, conn)
keyword_data = pd.read_sql_query(keyword_table, conn)

user_review_data = pd.merge(user_data, review_data, on='user_id')
place_category_data = pd.merge(place_data, category_data, on='category_id')
place_review_data = pd.merge(place_data, review_data, on='place_id')
user_review_place_data = pd.merge(user_review_data, place_data, on='place_id')
Place_review_category_data = pd.merge(place_review_data, place_category_data, on='place_id')


#print(Place_review_category_data)

#print(review_data)
# print(place_category_data['category_name'])
# print(place_keywords_match_data['name'])
# print(place_keywords_data['keywords_id'])
# print(place_review_data['contents'])
#print(place_category_data)

#print(place_keywords_data)
#print(place_keywords_match_data)
#print(place_review_data)
#########################################################################################################

#review로 뽑아낸 cosine 유사도 추천

okt = Okt()

keyword_dict = {
                '전통적인' : '전통적인', '옛날' : '전통적인', '전통미' : '전통적인', '고려' : '전통적인', '조선' : '전통적인', '조상' : '전통적인', '선조' : '전통적인', '역사' : '전통적인',
                '쇼핑' : '쇼핑', '물건' : '쇼핑', '샀':'쇼핑' ,
                '자연' : '자연', '풍경' : '자연', '야외' : '자연', 
                '관광' : '유명한' , '관광지' : '유명한', '유명한' : '유명한' ,
                '분위기' : '분위기 있는', '운치' : '분위기 있는',  '한적한' : '분위기 있는', '조용하고' : '분위기 있는', 
                '최신' : '신기한', '신기' : '신기한' , '처음보는' : '신기한', 
                '깔끔' : '깔끔한' , '깨끗' : '깔끔한',
                '힐링' : '힐링',
                '감동적인' : '감동', '감동' : '감동', '감격' : '감동',  
                '좋' : '좋아요', '너무너무' : '좋아요', '짱짱' : '좋아요', '짱':'좋아요', '좋았음' : '좋아요',
                '재밌' : '꿀잼','재미있' : '꿀잼', '잼' : '꿀잼', '재미' : '꿀잼', '꿀' : '꿀잼', '흥미':'꿀잼', '놀': '꿀잼', '놀았다' : '꿀잼', '놀았습니다' : '꿀잼', '재미있게' : '꿀잼', 
                '웃' : '웃음주의', '웃음' : '웃음주의', '웃기': '웃음주의', '코믹' : '웃음주의', '웃긴' : '웃음주의', '웃겨요': '웃음주의', '코미디':'웃음주의', '배꼽':'웃음주의','웃겼':'웃음주의','웃겨서':'웃음주의',
                '와' : '대박', '진짜' : '대박', '오' : '대박', '대단' : '대박', '강력':'대박', '역시':'대박', 
                '볼' : '볼만한', '괜찮':'볼만한',
                '즐겁' : '즐거운', '즐거웠': '즐거운', '신나': '즐거운', '스트레스' : '즐거운', '즐길': '즐거운', '즐기':'즐거운', '즐겁게': '즐거운',
                '매드니스' : '미친', '매드': '미친', '미쳤':'미친',
                '유쾌' : '꿀잼',
                '눈물' : '슬픔', '울' : '슬픔',
                '신선' : '신선한', '새로운':'신선한','신기':'신선한','다양':'신선한','센스':'신선한',
                '무섭' : '공포', '무서운' : '공포',
                '따뜻' : '따뜻한',
                '빠지' : '몰입', '열연' : '몰입', '공감' : '몰입', '집중' : '몰입', '여운':'몰입',
                '반전' : '소름',
                '놀라' : '감탄', '깜짝':'감탄',
                '탄탄' : '완벽',
                '매력' : '매력적인',
                '고요' : '고요한',
                '기대' : '기대가 되는',
                '감사' : '만족',
                '시간' : '시간순삭',
                '또' : 'N차 방문', '더' : 'N차 방문', '다음': 'N차 방문','다시': 'N차 방문', '자주': 'N차 방문',
                '추천' : '꿀잼보장',
                '꼭' : '강추', '완전' : '강추','박수':'강추','지인':'강추',
                '노래' : '고막 힐링', '매력' : '고막 힐링', '소리': '고막 힐링','목소리':'고막 힐링','음악':'고막 힐링',
                '기억' : '인상적인', '장면': '인상적인', '추억':'인상적인','인생':'인상적인','열심히':'인상적인','호흡':'인상적인','인상':'인상적인',
                '어린이' : '아이', '아기' : '아이', 
                '남편' : '가족', '아들' : '가족', '딸' : '가족', '부모' : '가족', '아빠' : '가족', '엄마' : '가족', '형':'가족',
                '남친' : '커플', '연인' : '커플', '데이트':'커플', '여친' : '커플',
                '혼자' : '혼자 갈 만한', 
                '함께' : '함께 갈 만한', '같이' : '함께 갈 만한',
                '둘' : '친구'
            }
stop_words = "에서 를 으로는 는 에 화 을 하는 이 습니 곳 . , 랑 수 많은 많음 사람 장소 느낌 느껴지는 있는 있어서 지는 이랑 이런 와 곳 가 인 적 수 곳 너무 에 는데 라도 어서 에서 이 어서 어요 과 는 부터 것 을 어요 고 게 던 은 었 내내 도 고 ㅋ ㅎ 나오 는 님 했 던 보다"
stop_words = set(stop_words.split(' '))
def place_recommendations(current_user_id, selected_category):

    # 현재 유저의 리뷰에서 키워드를 추출
    user_keywords_all=[]
    for i in range(len(Place_review_category_data['contents'])):
        if Place_review_category_data['user_id'][i]== current_user_id and Place_review_category_data['category_name'][i]== selected_category:

            user_review= Place_review_category_data['contents'][i]
            word_tokens = okt.morphs(user_review)
            result = [word for word in word_tokens if not word in stop_words]
            for word in result:
                user_keywords_all += [keyword_dict[word] if keyword_dict.get(word) else '']

  
   
    #키워드 카운트
    user_keywords_count = Counter(user_keywords_all)
    #빈도 높은 5개의 키워드 추출
    user_keywords_most = list(user_keywords_count.most_common(5))
    # 키워드 빈도가 2 이상인 키워드 추출
    user_keywords_selected = list(filter(lambda x : x[1] >= 1, user_keywords_most))

    user_keywords=[]
    for s in user_keywords_selected:
        user_keywords.append(s[0])
    
    # 리뷰가 없는 유저들 설문조사 기반 keyword 추출
    for i in range(len(keyword_data)):
        if keyword_data['user_id'][i]== current_user_id:
            user_keywords.append(keyword_data['survey_keyword'][i])


    # 해당 카테고리에 속하는 place의 모든 키워드 추출
    all_keywords= []
    for i in range(len(Place_review_category_data['contents'])):
        if Place_review_category_data['category_name'][i]== selected_category:
            
            place_review= Place_review_category_data['contents'][i]
            word_tokens = okt.morphs(place_review)
            result = [word for word in word_tokens if not word in stop_words]
            
            for word in result:
                all_keywords += [keyword_dict[word] if keyword_dict.get(word) else '']
    
    #키워드 카운트
    all_keywords_count = Counter(all_keywords)
    #빈도 높은 5개의 키워드 추출
    all_keywords_most = list(all_keywords_count.most_common(300))
    # 키워드 빈도가 2 이상인 키워드 추출
    all_keywords_selected = list(filter(lambda x : x[1] >= 2, all_keywords_most))

    all_place_keywords=[]
    for s in all_keywords_selected:
        all_place_keywords.append(s[0])



    # place 마다 키워드 추출
    lst= [set() for _ in range(len(place_data))]        #중복 제거를 위해 set으로 만들어줌
    k=1
    for i in range(1,len(place_review_data['contents'])):
        if place_review_data['place_id'][i-1]== place_review_data['place_id'][i]:

            place_review= place_review_data['contents'][i]
            word_tokens = okt.morphs(place_review)
            result = [word for word in word_tokens if not word in stop_words]
            for word in result:
                lst[k].add(keyword_dict[word] if keyword_dict.get(word) else '')

         
        else:
            k+=1
            place_review= place_review_data['contents'][i]
            word_tokens = okt.morphs(place_review)
            result = [word for word in word_tokens if not word in stop_words]
            for word in result:
                lst[k].add(keyword_dict[word] if keyword_dict.get(word) else '')
            
  

    set_user_keywords = set(user_keywords)
    set_all_keywords= set(all_keywords)

    


    # 기준이 되는 키워드와 벡터 키워드 리스트를 받아서 키워드별 빈도를 구하는 함수
    def make_matrix(feats, list_data):
        freq_list = []
        for feat in feats:
            freq = 0
            for word in list_data:
                if feat == word:
                    freq += 1
            freq_list.append(freq)
        return freq_list


    #로그인한 user keyword matrix
    my_matrix = np.array(make_matrix(set_all_keywords, set_user_keywords))
    

    #선택한 카테고리에 속하는 모든 여행지의 keyword matrix
    v_lst=[]
    for i in range(len(lst)):
        v_lst.append(np.array(make_matrix(set_all_keywords, lst[i])))




    # 코사인 유사도를 구하는 함수
    def cos_sim(a, b):
        return dot(a, b)/(norm(a)*norm(b))


    #place_id 와 코사인 유사도를 딕셔너리 형태로 저장
    dic=dict()
    for i in range(len(v_lst)):
        cs = cos_sim(my_matrix, v_lst[i])
        dic[i]=cs
   

    #코사인 유사도 높은 순서대로 정렬
    sorted_dic = sorted(dic.items(), reverse = True, key = lambda item: item[1])
  

    #인덱스 추출
    index_list=[]
    for i in sorted_dic:
        index_list.append(i[0])
    #상위 100개만
    index_list_100 = index_list[:100]


    info_list=[]
    for i in index_list_100:
        for j in range(len(place_data['place_id'])):
            if i == place_data['place_id'][j]:
                info_list.append(tuple([j,place_data['place_id'][j],place_data['addr'][j],place_data['score'][j],place_data['mapx'][j],place_data['mapy'][j],place_data['title'][j],place_data['image'][j],place_data['overview'][j]]))

    #return info_list
    #print(info_list)
    df=pd.DataFrame(info_list,columns=['recommend_id','place_id','addr','score','mapx','mapy','title','image','overview'])
    #print(df)

    def mysql_save(info_list):
        conn=pymysql.connect(host='j7d205.p.ssafy.io',
                    user='root',
                    password='betravelic205',
                    db='D205_2',
                    charset='utf8')

        cursor=conn.cursor()
        sql = "truncate recommendplace"
        cursor.execute(sql)
        

        #cursor=conn.cursor()
        sql="insert into recommendplace(recommend_id,place_id,addr,score,mapx,mapy,title,image,overview) values(%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        cursor.executemany(sql,info_list)
        conn.commit()
        conn.close()
    mysql_save(info_list)



current_user_id = 31
selected_category= "레저스포츠"   
print(place_recommendations(current_user_id, selected_category))


##############################################################################################


# from konlpy.tag import Okt
# from numpy import dot
# from numpy.linalg import norm
# import numpy as np
# from sklearn.feature_extraction.text import TfidfVectorizer
# from sklearn.metrics.pairwise import cosine_similarity
# from sklearn.decomposition import TruncatedSVD
# import pandas as pd
# import numpy as np
# import pymysql
# from eunjeon import Mecab


# conn = pymysql.connect(host='localhost',
#                         user='root',
#                         password='ssafyd205',
#                         db='D205_2',
#                         charset='utf8')





# place_table = "SELECT * FROM place"
# category_table = "SELECT * FROM place_category"
# place_keywords_table = "SELECT * FROM place_keywords"
# review_table = "SELECT * FROM review"
# all_keywords_table = "SELECT * FROM keywords"

# place_data = pd.read_sql_query(place_table, conn)
# category_data = pd.read_sql_query(category_table, conn)
# keywords_data = pd.read_sql_query(place_keywords_table, conn)
# review_data = pd.read_sql_query(review_table, conn)
# all_keywords_data = pd.read_sql_query(all_keywords_table, conn)



# place_category_data = pd.merge(place_data, category_data, on='category')
# place_keywords_data = pd.merge(place_data, keywords_data, on='place_id')
# place_review_data = pd.merge(place_data, review_data, on='place_id')
# place_keywords_match_data = pd.merge(place_keywords_data, all_keywords_data, on='keywords_id')




# #print(review_data)
# # print(place_category_data['category_name'])
# # print(place_keywords_match_data['name'])
# # print(place_keywords_data['keywords_id'])
# # print(place_review_data['contents'])
# #print(place_category_data)

# #print(place_keywords_data)
# #print(place_keywords_match_data)
# #print(place_review_data)
# #########################################################################################################

# #review로 뽑아낸 cosine 유사도 추천






# okt = Okt()

# # 방문지 별로 모든 리뷰 정보를 가져온다. 
# lst= [set() for _ in range(len(place_data))]        #중복 제거를 위해 set으로 만들어줌

# k=1
# for i in range(1,len(place_review_data['contents'])):
#     if place_review_data['place_id'][i-1]== place_review_data['place_id'][i]:

#         m = Mecab().pos(place_review_data['contents'][i], flatten=True) 
#         m_filtered = [x for x, y in m if y in ['NNG','XR']] 
#         for j in m_filtered:
#             lst[k].add(j)
#         # for j in okt.nouns(place_review_data['contents'][i]):
#         #     lst[k].add(j)
#     else:
#         k+=1
#         m = Mecab().pos(place_review_data['contents'][i], flatten=True) 
#         m_filtered = [x for x, y in m if y in ['NNG','XR']] 
#         for j in m_filtered:
#             lst[k].add(j)
#         # for j in okt.nouns(place_review_data['contents'][i]):
#         #     lst[k].add(j)

# print(place_review_data)
# print(lst)
# #set_lst= set(lst)
# #print(set_lst)
# selected_place_id = 1   #프론트에서 넘겨주는 place_id
# selected_list=lst[selected_place_id]



# # 기준이 되는 키워드와 벡터 키워드 리스트를 받아서 키워드별 빈도를 구하는 함수
# def make_matrix(feats, list_data):
#     freq_list = []
#     for feat in feats:
#         freq = 0
#         for word in list_data:
#             if feat == word:
#                 freq += 1
#         freq_list.append(freq)
#     return freq_list


# v_lst=[]
# for i in range(len(lst)):
#     #print(lst[i])
#     v_lst.append(np.array(make_matrix(selected_list, lst[i])))

# #print(v_lst)


# # sentence = '이 여행지는 여자친구와 함께 와서 즐길 수 있는 최적의 여행지이다. 매우 즐거운 시간을 보낼 수 있었다. 적이었다 힐링 할 수 있었다. 볼거리 휴식 체험 생동감 짜릿함 가성비 인상적이었다 이국적인 곳 ' 

# # m1 = Mecab().pos(sentence, flatten=True) 
# # m_filtered1 = [x for x, y in m1 if y in ['NNG','VV','VA','VA+ETM','VV+ETM' ]] 
# # print(m1)




# # 코사인 유사도를 구하는 함수
# def cos_sim(a, b):
#     return dot(a, b)/(norm(a)*norm(b))


# k= selected_place_id # 프론트로부터 받은 place_id
# dic=dict()
# for i in range(len(v_lst)-2):
#     if (i+1)!=k:
#         cs = cos_sim(v_lst[k],v_lst[i+1])
#         #print(f'{k}<=>{i+1} : {cs}')
#         dic[i+1]=cs
# #print(dic)
# sorted_dic = sorted(dic.items())
# #print(sorted_dic)
# index_list=[]
# for i in sorted_dic:
#     index_list.append(i[0])
# #print(index_list)    
# #print(place_data['place_id'])

# cosine_place_list=[]
# for i in index_list:
#     for j in range(len(place_data['place_id'])):
#         if i == place_data['place_id'][j]:
#             cosine_place_list.append(place_data['title'][j])
# print(cosine_place_list)

