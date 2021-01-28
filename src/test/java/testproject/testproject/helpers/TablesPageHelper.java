package testproject.testproject.helpers;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import testproject.testproject.Utils;

public class TablesPageHelper {

    public void checkRowsCount(String tableId, int count) {
        int rowsCount = getRowsCount(tableId);
        Assert.assertEquals(count, rowsCount);
    }

    public void checkColumnsCount(String tableId, int count) {
        int columnsCount = getColumnsCount(tableId);
        Assert.assertEquals(count, columnsCount);
    }

    public void sortColumn(String tableId, String header) {
        int columnIndex = getColumnIndexByHeader(tableId, header);
        WebElement cell = getCellByRowAndColumnIndex(tableId, 0, columnIndex);
        cell.click();
    }

    public void checkColumnValues(String tableId, String header, List<String> values) {
        int rowsCountWithoutHeader = getRowsCount(tableId) - 1;
        Assert.assertEquals(values.size(), rowsCountWithoutHeader);

        int columnIndex = getColumnIndexByHeader(tableId, header);
        for (int i = 1; i < rowsCountWithoutHeader; i++) {
            WebElement cell = getCellByRowAndColumnIndex(tableId, i, columnIndex);
            Assert.assertEquals("Wrong sort in row=" + (i + 1) + " column=" + (columnIndex + 1), values.get(i - 1), cell.getText());
        }
    }

    private int getRowsCount(String tableId) {
        List<WebElement> rows = Utils.webDriver.findElement(By.id(tableId)).findElements(By.tagName("tr"));
        return rows.size();
    }

    private int getColumnsCount(String tableId) {
        List<WebElement> rows = Utils.webDriver.findElement(By.id(tableId)).findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(0).findElements(By.tagName("th"));
        return columns.size();
    }

    private int getColumnIndexByHeader(String tableId, String header) {
        int columnIndex = -1;
        for (int i = 0; i < getColumnsCount(tableId); i++) {
            if (getCellByRowAndColumnIndex(tableId, 0, i).getText().equals(header)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex < 0) {
            throw new NotFoundException();
        }
        return columnIndex;
    }

    private WebElement getCellByRowAndColumnIndex(String tableId, int rowIndex, int columnIndex) {
        List<WebElement> rows = Utils.webDriver.findElement(By.id(tableId)).findElements(By.tagName("tr"));
        List<WebElement> columns = null;
        if (rowIndex == 0) {
            columns = rows.get(rowIndex).findElements(By.tagName("th"));
        } else {
            columns = rows.get(rowIndex).findElements(By.tagName("td"));
        }
        return columns.get(columnIndex);
    }

}
