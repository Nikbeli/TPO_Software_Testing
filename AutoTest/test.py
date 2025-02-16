import pytest
import undetected_chromedriver as uc
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
from webdriver_manager.chrome import ChromeDriverManager

from home_page import HomePage

class TestCatalog:
    def test_catalog_first_page(self):
        driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()))
        driver.maximize_window()
        auto_page = HomePage(driver)
        auto_page.open()
        
        auto_page.go_to_catalog()  # Теперь просто открываем ссылку
        
        driver.quit()

class TestFilter:
    def test_filter_by_hours_strong(self):
        driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()))
        driver.maximize_window()
        auto_page = HomePage(driver)
        auto_page.open()
        auto_page.go_to_catalog()
        auto_page.execute_filter_hours_strong(min_price=90, max_price=150)
        #assert auto_page.check_price_filter(min_price=500000, max_price=1500000)
        driver.quit()

class TestVinCheck:
    @pytest.mark.parametrize('vin_code', [('K804PT73')])
    def test_vin_check(self, vin_code):
        driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()))
        driver.maximize_window()
        auto_page = HomePage(driver)
        auto_page.open()
         # Перейти на страницу истории автомобиля
        auto_page.go_to_history()
        auto_page.check_vin(vin_code)
        # assets auto_page.get_vin_result().find(vin_code) != 1

        driver.quit()

class TestSearch:
    @pytest.mark.parametrize('search_text', [('BMW')])
    def test_search_positive(self, search_text):
        driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()))
        driver.maximize_window()
        auto_page = HomePage(driver)
        auto_page.open()
        auto_page.execute_search(search_text)
        assert auto_page.get_first_search_result().find(search_text) != -1
        driver.quit()

    @pytest.mark.parametrize('search_text', [('bfdbfdbfd')])
    def test_search_negative(self, search_text):
        driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()))
        driver.maximize_window()
        auto_page = HomePage(driver)
        auto_page.open()
        auto_page.execute_search(search_text)
        assert auto_page.get_empty_search().find(search_text) == -1
        driver.quit()

