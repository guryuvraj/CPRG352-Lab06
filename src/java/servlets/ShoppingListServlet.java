
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
          try 
          {
              if(action!=null)
              {
            if (action.equals("logout") ) 
            {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        } 
          }
        catch(NullPointerException ex)
        {
            
        }
        
        if (session.getAttribute("username") == null ) 
        {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } 
        else 
        {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
    } 

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String newItem = request.getParameter("newItem");
        String action = request.getParameter("action");
        ArrayList<String> allItems;
        

        if(action.equalsIgnoreCase("register"))
        {
            if(username==null || username.equals(""))
            {
                 System.out.println("Yes Null " + newItem);
                 request.setAttribute("loginmessage", "Error: Please enter your name");
                 getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                 return;
            }
            else
            {
                 session.setAttribute("username", username); 
            response.sendRedirect("ShoppingList");
            return;
            }
           
        }
        
        else if(action.equalsIgnoreCase("add"))
        {
           
            if(newItem==null || newItem.equals(""))
            {
                 System.out.println("Yes Null " + newItem);
                 request.setAttribute("message", "Error: Please enter an item name");
                 getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                 return;
            }
            else
            {
                 System.out.println("No Null " + newItem);
                allItems = (ArrayList<String>) session.getAttribute("itemList");
                
                if (allItems == null) 
                {
                    allItems = new ArrayList<>();
                }
                
                allItems.add(newItem);
                session.setAttribute("itemList", allItems);
            }
            
        }
        
        else if(action.equalsIgnoreCase("delete"))
        {
             allItems = (ArrayList<String>) session.getAttribute("itemList");
             allItems.remove((String) request.getParameter("toBeDeleted"));
             request.setAttribute("message", "Item got delete successfully");
             getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
             return;
             
        }

        response.sendRedirect("ShoppingList");
        return;
    } 
}
