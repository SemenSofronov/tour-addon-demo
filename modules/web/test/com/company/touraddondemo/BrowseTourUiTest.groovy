package com.company.touraddondemo

import com.company.touraddondemo.rules.DefaultCleanup
import com.company.touraddondemo.ui.LoginWindow
import com.company.touraddondemo.ui.ProductBrowser
import com.haulmont.masquerade.components.AppMenu
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

import static com.codeborne.selenide.Condition.exist
import static com.codeborne.selenide.Condition.text
import static com.codeborne.selenide.Selectors.*
import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.open
import static com.haulmont.masquerade.Components._$

class BrowseTourUiTest{

    @Before
    void login() {
        // open URL of an application
        open("http://localhost:8080/app")

        // login to the application
        _$(LoginWindow.class).loginButton.click()

        // open application menu item
        _$(AppMenu).openItem('application-touraddondemo', 'touraddondemo$Product.browse')
    }

    @Rule
    public TestRule defaultCleanup = new DefaultCleanup()

    @Test
    void checkCancelButton() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Отмена"))
                .click()

        $(byClassName(".shepherd-content")).shouldNot(exist)
    }

    @Test
    void checkCloseIcon() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(byClassName("shepherd-cancel-link"))
                .click()

        $(byClassName(".shepherd-content")).shouldNot(exist)
    }

    @Test
    void checkTutorialButton() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка создания"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка редактирования"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        _$(ProductBrowser).tourButton.click()

        $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")
                .should(exist)
    }

    @Test
    void goThroughBrowserTour() {
        def stepContent = $(withText("Обучение"))
                .shouldHave(text("Обучение началось!"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка создания"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка редактирования"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Кнопка удаления"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Панель фильтрации"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Назад"))
                .click()

        stepContent = $(byText("Кнопка удаления"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Далее"))
                .click()

        stepContent = $(byText("Панель фильтрации"))
                .closest(".shepherd-content")
                .should(exist)

        stepContent.find(withText("Завершить"))
                .click()

        $(byClassName(".shepherd-content")).shouldNot(exist)
    }
}
