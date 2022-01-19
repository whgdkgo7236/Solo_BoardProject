##엔티티 설계
1. MemberEntity
   1. id(long), email, password, name
2. 회원 : 게시글 = 1 : N 관계
3. 회원 : 댓글 = 1 : N 관계
4. 게시글 : 댓글 = 1 : N 관계
#### MemberEntity를 추가하고 게시글 , 댓글과의 연관관계 매핑을 구현해보자.
1. 회원이 글을 작성 하는 기능까지 만들기
