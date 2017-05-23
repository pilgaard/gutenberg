/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

/**
 *
 * @author Andreas
 */
public interface IWebController{
    
    public String DoSomething(String msg);
    public String GetBooksByCity(String cityName);
    public String GetCitiesByBookTitle(String bookTitle);
}
