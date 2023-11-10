package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeTest {

    @Test
    @DisplayName("올바른 메뉴로 메뉴 분류를 조회시 해당 메뉴 분류를 반환한다.")
    void returnMenuTypeFindByCorrectMenu() {
        //given
        Menu menu = Menu.ZERO_COLA;
        //when
        MenuType findMenuType = MenuType.findByMenu(menu);
        //then
        assertThat(findMenuType)
            .isEqualTo(MenuType.BEVERAGE)
            .extracting("title")
            .isEqualTo("음료");
    }

    @Test
    @DisplayName("유효하지 않은 메뉴로 메뉴 분류를 조회시 예외를 발생시킨다.")
    void throwExceptionFindMenuTypeByInCorrectMenu() {
        //given
        Menu menu = null;
        //when //then
        assertThatThrownBy(() -> MenuType.findByMenu(menu))
            .isInstanceOf(IllegalArgumentException.class);
    }
}