package Beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class indexBean
{
  InputStream fileName;
  String idDecompteparameter;
  HttpServletRequest request;
  String name = "ttttt";
  
  public indexBean()
  {
    this.request = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest());
    this.idDecompteparameter = this.request.getParameter("decomptID");
    
    System.out.println("======================================================");
    System.out.println("idDecompteparameter " + this.idDecompteparameter);
    if (this.idDecompteparameter != null) {
      try
      {
        FacesContext.getCurrentInstance().getExternalContext().dispatch("fileLocator.xhtml?decomptID=" + this.idDecompteparameter);
      }
      catch (IOException ex)
      {
        Logger.getLogger(indexBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
}
