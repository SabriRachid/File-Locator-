import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearcheFile
  extends HttpServlet
{
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try
    {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet SearcheFile</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet SearcheFile at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
    finally
    {
      out.close();
    }
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    performTask(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }
  
  private void performTask(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String pdfFileName = "ksa.pdf";
    String contextPath = getServletContext().getRealPath(File.separator);
    File pdfFile = new File(contextPath + pdfFileName);
    
    response.setHeader("Expires", "0");
    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
    
    response.setHeader("Pragma", "public");
    
    response.setContentType("text/html");
    
    response.addHeader("Content-Disposition", "attachment; filename=" + pdfFileName);
    response.setContentLength((int)pdfFile.length());
    
    FileInputStream fileInputStream = new FileInputStream(pdfFile);
    OutputStream responseOutputStream = response.getOutputStream();
    int bytes;
    while ((bytes = fileInputStream.read()) != -1) {
      responseOutputStream.write(bytes);
    }
  }
  
  public String getServletInfo()
  {
    return "Short description";
  }
}
