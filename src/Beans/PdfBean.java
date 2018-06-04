package Beans;

import java.io.InputStream;
import java.io.PrintStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import jpa.DbDao;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class PdfBean
{
  StreamedContent pdfDocument;
  static InputStream fileName;
  String idDecompteparameter;
  HttpServletRequest request;
  static int i;
  
  public StreamedContent getPdfDocument()
  {
    try
    {
      i += 1;
      System.out.println("i:" + i);
      if (i == 1)
      {
        DbDao db = new DbDao();
        this.request = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest());
        this.idDecompteparameter = this.request.getParameter("decomptID");
        System.out.println("idDecompteparameter " + this.idDecompteparameter);
        fileName = db.FindFile(this.idDecompteparameter);
        if (fileName == null)
        {
          String uri = "faces/error.xhtml";
          FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
        }
      }
      else if (i == 2)
      {
        i = 0;
      }
    }
    catch (Exception e)
    {
      System.err.println("Erreur :  bd");
    }
    return new DefaultStreamedContent(fileName, "application/pdf");
  }
}
