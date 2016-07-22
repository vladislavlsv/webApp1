package myApp.controllers;

import myApp.Exceptions.DaoBusinessException;
import myApp.Exceptions.DaoSysException;
import myApp.Exceptions.NoSuchEntityException;
import myApp.dao.ProductDao;
import myApp.entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductController extends HttpServlet {

public static final String PAGE_ERROR = "error.jsp";
public static final String PAGE_OK = "product.jsp";
public static final String PARAM_ID = "id";
public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";

private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null){
            try{
            Integer id = Integer.valueOf(idStr);
            Product model = productDao.selectById(id);
            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW,model);
            req.getRequestDispatcher(PAGE_OK).forward(req,resp);
                return;
        }catch (NumberFormatException | DaoSysException | DaoBusinessException e){
                /*NOP*/
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}