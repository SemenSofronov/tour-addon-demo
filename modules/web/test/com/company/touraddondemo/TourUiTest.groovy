package com.company.touraddondemo

import com.company.touraddondemo.ui.LoginWindow
import com.company.touraddondemo.ui.ProductBrowser
import com.company.touraddondemo.ui.ProductEdit
import com.haulmont.masquerade.components.AppMenu
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import static com.codeborne.selenide.Condition.text
import static com.codeborne.selenide.Selectors.*
import static com.codeborne.selenide.Selenide.*
import static com.haulmont.masquerade.Components._$
import static com.company.touraddondemo.util.UiTestManager.executeSql

class TourUiTest {

    @Before
    void login() {
        // open URL of an application
        open("http://localhost:8080/app")

        // login to the application
        _$(LoginWindow.class).loginButton.click()

        // open application menu item
        _$(AppMenu).openItem('application-touraddondemo', 'touraddondemo$Product.browse')
    }

    @Test
    void checkCancelButton() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")

        stepContent.find(withText("Отмена"))
                .click()

        Assert.assertFalse($(byClassName(".shepherd-content")).exists())

        close()
    }

    @Test
    void goThroughEditorTour() {
        clearDb()

        $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")

        _$(ProductBrowser).createBtn.click()

        def stepContent = $(withText("Экран"))
                .shouldHave(text("Экран редактирования"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Сгруппированные поля"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Действия экрана"))
                .closest(".shepherd-content")

        stepContent.find(withText("Завершить"))
                .click()

        Assert.assertFalse($(byClassName(".shepherd-content")).exists())

        close()
    }

    @Test
    void goThroughEditorTourTwice() {
        clearDb()

        $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")

        _$(ProductBrowser).createBtn.click()

        $(withText("Экран"))
                .shouldHave(text("Экран редактирования"))
                .closest(".shepherd-content")


        _$(ProductEdit).windowClose.click()

        _$(ProductBrowser).createBtn.click()

        Assert.assertFalse($(byClassName(".shepherd-content")).exists())

        close()
    }

    @Test
    void checkCloseIcon() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")

        stepContent.find(byClassName("shepherd-cancel-link"))
                .click()

        Assert.assertFalse($(byClassName(".shepherd-content")).exists())

        close()
    }

    @Test
    void checkTutorialButton() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка создания"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка редактирования"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        _$(ProductBrowser).tourButton.click()

        $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")

        close()
    }

    @Test
    void goThroughBrowserTour() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка создания"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка редактирования"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка удаления"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Панель фильтрации"))
                .closest(".shepherd-content")

        stepContent.find(withText("Назад"))
                .click()

        stepContent = $(byText("Кнопка удаления"))
                .closest(".shepherd-content")

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Панель фильтрации"))
                .closest(".shepherd-content")

        stepContent.find(withText("Завершить"))
                .click()

        Assert.assertFalse($(byClassName(".shepherd-content")).exists())

        close()
    }

    def clearDb() {
        executeSql("delete from SEC_USER_SETTING")
    }
}
