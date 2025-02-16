import time

from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec
from selenium.webdriver.common.keys import Keys

from base_page import BasePage

class HomePage(BasePage): 
     # Сайт 
    url = "https://auto.ru/"

    __history_link = "https://auto.ru/history/"
    __catalog_link = "https://auto.ru/catalog/cars/"

    # Поля для поиска
    __input_search = (By.XPATH, "//div[contains(@class, 'SearchLineSuggest__input')]//input")
    __search_results = (By.XPATH, "//div[contains(@class,'ListingHead__content')]//h1")
    __empty_search = (By.XPATH, "//div[contains(@class, 'Index')]//div")
    
    # Фильтрация
    __filter_price_from = (By.XPATH, "//label[contains(@class, 'TextInput TextInput_size_l TextInput_has-clear TextInput_has-placeholder TextInput_placeholderShowWithValue')]//input")
    __filter_price_to = (By.XPATH, "//label[contains(@class, 'TextInput TextInput_size_l TextInput_has-clear TextInput_has-placeholder TextInput_placeholderShowWithValue')]//input")
    __filter_button = (By.XPATH, "//button[contains(@class,'Button2-DDOYo Button2_themeType_primary-bnQga Button2_size_m-btcI2 Button2_color_black-NJF2l CatalogFilters__button-lGshV')]")
    
    # Каталог
    __catalog_section = (By.XPATH, "//div[contains(@class, 'Index')]")
    __catalog_first_page = (By.XPATH, "//div[contains(@class,'BaseApp__content AppCatalog__content-gicEX')]")
    # Проверка авто по VIN
    __vin_input = (By.XPATH, "//div[contains(@class, 'VinCheckInput3__controls-EZidM')]//input")
    __vin_check_button = (By.XPATH, "//button[contains(@class,'Button2-DDOYo Button2_themeType_primary-bnQga Button2_size_l-giThR Button2_color_lightStatic-hPynB')]")
    __vin_result = (By.XPATH, "//div[contains(@class,'VinReportPreviewDesktop__mmm')]//div")
    
    def __init__(self, driver: WebDriver):
        super().__init__(driver)

    # Перейти на страницу истории автомобиля по VIN
    def go_to_history(self):
        super()._open_url(self.__history_link)

    def go_to_catalog(self):
        super()._open_url(self.__catalog_link)

    # Поиск автомобиля
    def execute_search(self, search_text: str):
        # Кликаем на поле поиска через super()
        super()._click(self.__input_search)
        time.sleep(4)

        # Заполняем поле поисковым текстом через super()
        super()._type(self.__input_search, search_text)
        time.sleep(2)  # Дополнительная задержка перед отправкой, если необходимо

        # Вместо клика по кнопке поиска, отправляем форму с помощью Enter
        element = self._find(self.__input_search)
        element.send_keys(Keys.RETURN)  # Нажимаем Enter для отправки формы
        time.sleep(4)
    
    # Фильтрация по цене
    def execute_filter_hours_strong(self, min_price: int, max_price: int):
        min_price_input = self._find(self.__filter_price_from)  # Найти поле ввода
        min_price_input.clear()
        min_price_input.send_keys(str(min_price))  # Ввести минимальную цену
        time.sleep(1)

        max_price_input = self._find((self.__filter_price_to))  # Поле для максимальной цены
        max_price_input.clear()
        max_price_input.send_keys(str(max_price))  # Ввести максимальную цену
        time.sleep(1)

        super()._click(self.__filter_button)  # Нажать кнопку фильтрации
        time.sleep(2)
    
    # Открытие каталога
    def open_catalog(self):
        super()._click(self.__catalog_section)
        time.sleep(2)
    
    # Проверка авто по VIN
    def check_vin(self, vin: str):
        super()._type(self.__vin_input, vin)
        time.sleep(1)
        super()._click(self.__vin_check_button)
        time.sleep(2)
    
    # Открыть сайт
    def open(self):
        super()._open_url(self.url)

    # Получить первый результат поиска
    def get_first_search_result(self) -> str:
        WebDriverWait(self._driver, 10).until(
            ec.visibility_of_element_located(self.__search_results)
        )
        return super()._get_text(self.__search_results, time=3)

    # Проверка пустого поиска
    def get_empty_search(self) -> str:
        WebDriverWait(self._driver, 10).until(
            ec.visibility_of_element_located(self.__empty_search)
        )
        return super()._get_text(self.__empty_search, time=3)

    # Получить первый результат после фильтрации
    def get_first_filtered_result(self) -> str:
        return super()._get_text(self.__filtered_results, time=3)

    # Проверка загрузки первой страницы каталога
    def get_first_catalog_result(self) -> str:
        WebDriverWait(self._driver, 10).until(
            ec.visibility_of_element_located(self.__catalog_first_page)
        )
        return super()._get_text(self.__catalog_first_page, time=3)
    
    # Получить результат проверки по VIN
    def get_vin_result(self) -> str:
        WebDriverWait(self._driver, 10).until(
            ec.visibility_of_element_located(self.__vin_result)
        )
        return super()._get_text(self.__vin_result, time=3)