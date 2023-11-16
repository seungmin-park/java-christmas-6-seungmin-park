package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    @DisplayName("올바른 메류 이름으로 메뉴를 조회시 해당 메뉴를 반환한다.")
    void returnMenuFindMenuByCorrectMenuName() {
        //given
        String menuName = "양송이수프";
        //when
        Menu findMenu = Menu.findByMenuName(menuName);
        //then
        assertThat(findMenu)
            .extracting("name")
            .isEqualTo(menuName);
    }

    @Test
    @DisplayName("잘못된 메뉴 이름으로 메뉴를 조회시 에러를 발생시킨다.")
    void throwExceptionFindMenuByInCorrectMenuName() {
        //given
        String menuName = "앙송이스프";
        //when //then
        assertThatThrownBy(() -> Menu.findByMenuName(menuName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}