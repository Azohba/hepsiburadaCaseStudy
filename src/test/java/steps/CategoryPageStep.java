package steps;

import com.thoughtworks.gauge.Step;
import pageElements.Base;
import pages.CategoryDetailPage;
import pages.CategoryPage;
import pages.ProducDetailPage;

public class CategoryPageStep {

    CategoryPage connectCategoryPage = CategoryPage.getInstance();
    CategoryDetailPage connectCategoryDetailPage = new CategoryDetailPage();
    Base connectBasePage = Base.getInstance();


    @Step("Click <> category")
    public void selectFirstProduct(String xpath) {
        connectCategoryPage.clickChoosenCategory(xpath);
       // connectBasePage.check();

    }


    @Step("Click first product")
    public void clickFirstProduct() {
        connectCategoryDetailPage.opendProductDetail();
    }
}
