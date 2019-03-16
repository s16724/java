package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
public class CreditServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String kwotaKredytu= req.getParameter("kwotaKredytu");
        String iloscRat= req.getParameter("iloscRat");
        String oprocentowanie= req.getParameter("oprocentowanie");
        String oplataStala= req.getParameter("oplataStala");
        String rodzajRat= req.getParameter("rodzajRat");

        if (kwotaKredytu==null || kwotaKredytu.isEmpty()||
        iloscRat==null || iloscRat.isEmpty()||
        oprocentowanie==null || oprocentowanie.isEmpty()||
        oplataStala== null || oplataStala.isEmpty()||
        rodzajRat == null || rodzajRat.isEmpty())
        {
            resp.sendRedirect("/bad.jsp");
        }
        else
        {
            double kwotaKredytu1 = Double.parseDouble(req.getParameter("kwotaKredytu"));
            int iloscRat1 = Integer.parseInt(req.getParameter("iloscRat"));
            double oprocentowanie1 = Double.parseDouble(req.getParameter("oprocentowanie"));
            double oplataStala1 = Double.parseDouble(req.getParameter("oplataStala"));
            String rodzajRat1 = req.getParameter("rodzajRat");

            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter writer = resp.getWriter();
            writer.append("<!DOCTYPE html>\r\n")
                    .append("<html>\r\n")
                    .append("<head>\r\n")
                    .append("<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"ISO-8859-1\">\r\n")
                    .append("<title>Kalukator kredytowy</title>\r\n")
                    .append("</head>\r\n")
                    .append("<body>\r\n")
                    .append("<table>\r\n")
                    .append("<tr>\r\n")
                    .append("<th>Nr raty</th>\r\n")
                    .append("<th>Kwota kapitalu</th>\r\n")
                    .append("<th>Kwota odsetek</th>\r\n")
                    .append("<th>Oplaty stale</th>\r\n")
                    .append("<th>Calkowita kwota raty</th>\r\n")
                    .append("</tr>\r\n");
            double oplatyRata =oplataStala1/iloscRat1;
            if (rodzajRat1.equals("stala_wysokosc_rat"))
            {
                for (int id = 1, i = 0; id <= iloscRat1; id++, i++) {
                    double wspolczynnik = 1+(oprocentowanie1/12);
                    double kapitalowaCzescRaty =kwotaKredytu1 * oprocentowanie1/12 * Math.pow(wspolczynnik, i)
                            / (Math.pow(wspolczynnik, iloscRat1) - 1);
                    double odsetkowaCzescRaty = kwotaKredytu1 * oprocentowanie1/12 * (Math.pow(wspolczynnik, iloscRat1)
                            - Math.pow(wspolczynnik, i)) / (Math.pow(wspolczynnik, iloscRat1) - 1);
                    double calkowitaKwotaRaty = kapitalowaCzescRaty + oplatyRata + odsetkowaCzescRaty;
                    writer.append("<tr>\r\n")
                            .append("<td>" + id + "</td>\r\n")
                            .append("<td>" + kapitalowaCzescRaty + "</td>\r\n")
                            .append("<td>" + odsetkowaCzescRaty + "</td>\r\n")
                            .append("<td>" + oplatyRata + "</td>\r\n")
                            .append("<td>" + calkowitaKwotaRaty + "</td>\r\n")
                            .append("</tr>\r\n");
                }

            }
            else if(rodzajRat.equals("raty_malejace"))
            {

                    for (int id = 1, i = 0; id <= iloscRat1; id++, i++) {
                        double kapitalowaCzescRaty = kwotaKredytu1/iloscRat1;

                        double calkowitaKwotaRaty = kapitalowaCzescRaty + oplatyRata ;
                        writer.append("<tr>\r\n")
                                .append("<td>" + id + "</td>\r\n")
                                .append("<td>" + kapitalowaCzescRaty + "</td>\r\n")
                                .append("<td>" + oplatyRata + "</td>\r\n")
                                .append("<td>" + calkowitaKwotaRaty + "</td>\r\n")
                                .append("</tr>\r\n");
                    }
            }
            writer.append("</table>\r\n")
                    .append("</body>\r\n")
                    .append("</html>\r\n");
        }

    }

}
