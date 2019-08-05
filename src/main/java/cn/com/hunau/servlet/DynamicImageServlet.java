package cn.com.hunau.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DynamicImageServlet
 */
public class DynamicImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DynamicImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置信息类型：图片类型  Mime type  text/html
				response.setContentType("image/jpeg");
				// 画字符串
				String randomStr = this.createRandom();
				// 将图片中显示的文字，保存到session中，以被后面的验证码验证
				request.getSession().setAttribute("code",randomStr);
				// 创建图像
				int width = 60;
				int height = 30;
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				// 获取画图上下文   画笔
				Graphics2D g = (Graphics2D) image.getGraphics();
				// 填充图片背景
				g.setColor(Color.yellow);
				g.fillRect(0, 0, width, height);
				
				g.setColor(Color.black);
				g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				g.drawString(randomStr, 8, height/2 +5);
				// 画边框
				g.setColor(Color.black);
				g.drawRect(0, 0, width - 1, height - 1);
				Random random = new Random();  
				 g.setColor(Color.gray);  
				    for (int i = 0; i < 30; i++) {  
				        int x = random.nextInt(width);  
				        int y = random.nextInt(height);  
				        int x1 = random.nextInt(12);  
				        int y1 = random.nextInt(12);  
				        g.drawLine(x, y, x + x1, y + y1);  
				    }  
				// 销毁上下文
				g.dispose();
				// 将图片输出到输出流
				/*ServletOutputStream out = response.getOutputStream();
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(image);

				out.flush();
				out.close();*/


				 // 图象生效
		        g.dispose();
		        // 输出图象到页面
		        ImageIO.write(image, "JPEG", response.getOutputStream());
		        // 加上下面代码,运行时才不会出现java.lang.IllegalStateException: getOutputStream() has
		        // already been called ..........等异常
		        response.getOutputStream().flush();
		        response.getOutputStream().close();
		        response.flushBuffer();
	}
	
	private String createRandom() {
		StringBuffer strBuff = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			char random = (char) (Math.random() > 0.50 ? ((int) (Math.random() * 25)) + 65
					: ((int) (Math.random() * 25)) + 97) ;
			strBuff.append(random );
		}
		return strBuff.toString();
	}

}
