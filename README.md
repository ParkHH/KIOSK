# KIOSK Project
#### ● 주제 : 자동 주문 프로그램
#### ● 개발기간 : 19. 02. 12 ~ 19. 02. 21 (9일)
#### ● 개발환경
> 1) JavaSE (jdk 1.8)
> 2) Eclipse
> 3) OracleXE

#### ● 구현 UI
## Client 
<hr/>
1. Client_Main
<img src="https://blogfiles.pstatic.net/MjAxOTA1MDJfMjUx/MDAxNTU2NzIzNzMwMjY5.WNKYUqHr6QekYk2byX1KVSl7Nb9b3dg_Ky2YNUAcz9gg.oWK-SOlnMrNSCRRL0VMNDaZUI_yhghKeovpJqWpOgAog.PNG.phh_92/Client_Main.png?type=w2" width="550px"/>
<br>
- DB 에 등록되어있는 상품정보를 이미지로 표현하는 페이지
- 상품 이미지는 Eclipse classpath 로 등록한 resource folder 내부에 있는 Image 로 출력함
<br><br>
2. Food_box_info
<img src="https://blogfiles.pstatic.net/MjAxOTA1MDJfODgg/MDAxNTU2NzI0NDIzMDQ2.SpH101HwkxlNyKNaNOVjA2EqFZjJR24wF4NW8HrvaXwg.VoHyGOb_qsRJUzKfBds0CiArdBFfvQ-dez0uQ3uyDWAg.PNG.phh_92/foodbox_product_info.png?type=w2"/>
<br>
- 표현된 상품 박스 Component 에 MouseOver Event 를 주어 해당 상품 정보를 표현하는 Component 를 Overlay 되게 한다.
<br><br>
3. Product_info_window
<img src="https://blogfiles.pstatic.net/MjAxOTA1MDJfODcg/MDAxNTU2NzIzNzMzMzA1.MOtN2_S7jyPm2s1sVXJOAp04uar7tb7r4Gs3xG60rJAg.yV6GJcKT1T0ExQ7KkLQV2xXUWDnzHwL7NUl1u2OATWAg.PNG.phh_92/selected_box.png?type=w2" width="550px"/>
<br>
- 위 2번에서 상품 이미지를 클릭시 Component 에 부여한 MouseClick Event 가 동작하여 본 Component 가 출력된다.
- 본 Component 에서는 선택한 상품에 대한 상세 주문을 설정 할 수 있다.
- 선택할 수 있는 세부 주문 설정은 <b>상품종류, 음료, 사이드 메뉴</b> 이다.
    -> 초기값은 단품메뉴이며 단품메뉴일 경우에는 음료와 사이드메뉴를 선택할 수 없다.
<br><br>
