from selenium.common import NoSuchElementException
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec

class BasePage:
    # Драйвер для работы с веб-страницами
    def __init__(self, driver: WebDriver):
        self._driver = driver

    def _scroll_into_view(self, locator: tuple):
        element = self._find(locator)
        self._driver.execute_script("arguments[0].scrollIntoView(true);", element)

    # Поиск и возврат одного элемента на веб-странице с помощью переданного локатора
    def _find(self, locator: tuple) -> WebElement:
        return self._driver.find_element(*locator)
    
    # Ввод текста в элемент на веб-странице
    def _type(self, locator: tuple, text: str, time: int = 10):
        # Прокручиваем элемент в видимую область
        self._scroll_into_view(locator)
        self._wait_until_element_is_visible(locator, time)
        self._find(locator).send_keys(text)

            # выполнение клика по элементу на веб-странице
    def _click(self, locator: tuple, time: int = 10):
        self._wait_until_element_is_visible(locator, time)
        self._find(locator).click()

    # ожидание появления элемента на веб-странице до тех пор, пока он не станет видимым
    def _wait_until_element_is_visible(self, locator: tuple, time: int = 10):
        wait = WebDriverWait(self._driver, time)
        wait.until(ec.visibility_of_element_located(locator))

    # открытие указанного URL веб-страницы
    def _open_url(self, url: str):
        self._driver.get(url)

    # получение текстового содержимого элемента, найденного по указанному локатору.
    def _get_text(self, locator: tuple, time: int = 3) -> str:
        self._wait_until_element_is_visible(locator, time)
        return self._find(locator).text.encode("utf-8").decode("utf-8")
