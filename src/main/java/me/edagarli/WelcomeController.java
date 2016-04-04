package me.edagarli;

import me.edagarli.test.service.HelloEdagarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getWelcome() {

		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}

		ModelAndView model = new ModelAndView("welcome");
		model.addObject("msg", "hello,edagarli");
		return model;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView getTestMsg() {

		ModelAndView model = new ModelAndView("welcome");
		String msg = "";

//		System.out.println( Thread.currentThread().getName()  + " --> " + helloEdagarService.hello());

		for (int i = 0; i<30; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						Thread.sleep(2000);
						System.out.println( Thread.currentThread().getName()  + " --> " + helloEdagarService.hello());
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}).start();
		}

		model.addObject("msg", msg);
		return model;
	}

	@Autowired
	private HelloEdagarService helloEdagarService;
}