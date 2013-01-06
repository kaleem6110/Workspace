package lu.globalepic.hook;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author kaleem.mohammed
 *
 */
public class LoginStrutsAction extends BaseStrutsAction {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			String name = ParamUtil.get(request, "name", "World");

			request.setAttribute("name", name);
			System.out.println("LoginStrutsAction .execute ");
			return "/web/apps/home";
		}

}
