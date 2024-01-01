# 한글 초성, 자모 검색을 위한 Opensearch Plugin 2.11.1
아래의 프로그램을 Opensearch에서 사용할 수 있도록 설정을 변경하였습니다.

# 한글 초성, 자모 검색을 위한 Elasticsearch Plugin
* [티몬 개발자 블러그](http://blog.naver.com/PostView.nhn?blogId=tmondev&logNo=220918935030&redirect=Dlog&widgetTypeCall=true)를 참고 하여 구현하였습니다. 
* 정확히는 5.2.2로 포팅 한 것이 되겠네요. 티몬 개발자 블러그에 정확한 라이선스가 명시가 되어 있지 않아 댓글로 여쭙고 작업 진행 하였습니다.
* Elasticsearch의 ngram을 이용하여 한글 초성 및 자모 검색을 하고 싶었습니다.
* 자세한 사용 방법은 [README.md](README.md) 참고 부탁 드립니다. 

opensearch-plugin install file:///backup/opensearch-hangul-jamo-analyzer-2.8.0.zip