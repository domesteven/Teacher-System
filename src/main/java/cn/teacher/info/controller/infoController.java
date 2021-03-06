package cn.teacher.info.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.bean.Attach;
import cn.bean.ProjectLecture;
import cn.bean.ProjectPerson;
import cn.bean.ProjectPublish;
import cn.bean.ProjectSocialservice;
import cn.bean.TaskCompany;
import cn.bean.TaskDirectortournament;
import cn.bean.TaskGraduation;
import cn.bean.TaskTeaching;
import cn.bean.TaskTutor;
import cn.bean.Teacher;
import cn.tdog.utils.ExportExcelParam;
import cn.tdog.utils.ExportExcelUtil;
import cn.tdog.utils.FormatUtil;
import cn.teacher.info.service.infoServiceIfc;

@Controller
public class infoController {
	private static final transient Logger log = Logger
			.getLogger(infoController.class);

	@Autowired
	private infoServiceIfc infoService;

	@Resource
	private Map<String, String> myconfig;

	@RequestMapping(value = "/saveInfo")
	@ResponseBody
	public ModelAndView saveInfo(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@Validated Teacher teacher, HttpServletRequest request,
			HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			log.info("============文件上传开始");
			if (!file.isEmpty()) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				fileName = file.getOriginalFilename();
				File dir = new File(path, fileName);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// MultipartFile自带的解析方法
				file.transferTo(dir);
				teacher.setImage(fileName);
			} else {
				teacher.setImage(((Teacher) request.getSession().getAttribute(
						"userinfo")).getImage());
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		teacher.setAuthorlever(((Teacher) request.getSession().getAttribute(
				"userinfo")).getAuthorlever());
		if (teacher.gettId() != null) {
			infoService.updateInfo(teacher);
		} else {
			infoService.insertInfo(teacher);
		}
		request.getSession().setAttribute("userinfo", teacher);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("showInfo");
		return modelAndView;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/goShowInfoPage")
	@ResponseBody
	public ModelAndView goShowInfoPage(@Validated Teacher teacher,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("showInfo");
		return modelAndView;
	}

	@RequestMapping(value = "/goEditInfoPage")
	@ResponseBody
	public ModelAndView goEditInfoPage(@Validated Teacher teacher,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editInfo");
		return modelAndView;
	}

	@RequestMapping(value = "/goUploadPage")
	@ResponseBody
	public ModelAndView goUploadPage(@Validated Teacher teacher,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload");
		return modelAndView;
	}

	@RequestMapping(value = "/delTaskTeaching")
	@ResponseBody
	public Map delTaskTeaching(@Validated TaskTeaching taskTeaching,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTaskTeaching(taskTeaching);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/delTaskCompany")
	@ResponseBody
	public Map delTaskCompany(@Validated TaskCompany taskCompany,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTaskCompany(taskCompany);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/delTaskGraduation")
	@ResponseBody
	public Map delTaskGraduation(@Validated TaskGraduation taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTaskGraduation(taskBean);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/saveTaskTeaching")
	public String saveTaskTeaching(@Validated TaskTeaching taskTeaching,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String uuid1 = UUID.randomUUID().toString().replaceAll("-", "");
			taskTeaching.setCode(uuid);
			taskTeaching.setAttach(uuid1);
			infoService.insert(taskTeaching);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTeachingTask";
	}

	@RequestMapping(value = "/saveTaskCompany")
	public String saveTaskCompany(@Validated TaskCompany taskCompany,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			taskCompany.setAttach(uuid);
			infoService.insert(taskCompany);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskCompany";
	}

	@RequestMapping(value = "/saveTaskGraduation")
	public String saveTaskCompany(@Validated TaskGraduation taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			taskBean.setAttach(uuid);
			infoService.insert(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskGraduation";
	}

	@RequestMapping(value = "/editTaskTeaching")
	public String editTaskTeaching(@Validated TaskTeaching taskTeaching,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {

			infoService.update(taskTeaching);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTeachingTask";
	}

	@RequestMapping(value = "/editTaskCompany")
	public String editTaskCompany(@Validated TaskCompany taskCompany,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {

			infoService.update(taskCompany);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskCompany";
	}

	@RequestMapping(value = "/editTaskGraduation")
	public String editTaskGraduation(@Validated TaskGraduation taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {

			infoService.update(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskGraduation";
	}

	@RequestMapping(value = "/selectTaskTeachingById")
	@ResponseBody
	public Map selectTaskTeachingById(@Validated TaskTeaching taskTeaching,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			TaskTeaching bean = infoService
					.selectTaskTeachingById(taskTeaching);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/selectTaskCompanyById")
	@ResponseBody
	public Map selectTaskCompanyById(@Validated TaskCompany taskCompany,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			TaskCompany bean = infoService.selectTaskCompanyById(taskCompany);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/selectTaskGraduationById")
	@ResponseBody
	public Map selectTaskGraduationById(@Validated TaskGraduation taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			TaskGraduation bean = infoService.selectTaskCompanyById(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/goTeachingTask")
	@ResponseBody
	public ModelAndView goTeachingTask(@Validated TaskTeaching bean,
			String page, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("teachingTask");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			if (bean.getType() != null && bean.getType() != "") {
				modelAndView.addObject("searchType", bean.getType());
			}
			//时间
			if (bean.getTime() != null ) {
				Calendar c = Calendar.getInstance();
				c.setTime( bean.getTime());
				int searchTime = c.get(Calendar.YEAR);
				modelAndView.addObject("searchTime", searchTime);
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}

			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<TaskTeaching> list = infoService.selectAllTaskTeaching(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService.selectTaskTeachingByPage(bean, startRow,
					pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/goTaskCompany")
	@ResponseBody
	public ModelAndView goTaskCompany(@Validated TaskCompany bean, String page,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("taskCompany");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<TaskCompany> list = infoService.selectAllTaskCompany(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService
					.selectTaskCompanyByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/goTaskGraduation")
	@ResponseBody
	public ModelAndView goTaskGraduation(@Validated TaskGraduation bean,
			String page, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("taskGraduation");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<TaskGraduation> list = infoService.selectAllTaskCompany(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService
					.selectTaskCompanyByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	// 学科竞赛指导任务controller--------------------开始
	@RequestMapping(value = "/goTaskDirectortournament")
	@ResponseBody
	public ModelAndView goTaskDirectortournament(
			@Validated TaskDirectortournament bean, String page,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("taskDirectortournament");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			//时间
			if (bean.getTime() != null ) {
				Calendar c = Calendar.getInstance();
				c.setTime( bean.getTime());
				int searchTime = c.get(Calendar.YEAR);
				modelAndView.addObject("searchTime", searchTime);
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<TaskDirectortournament> list = infoService.selectAllTask(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService.selectTaskByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/selectTaskDirectortournamentById")
	@ResponseBody
	public Map selectTaskDirectortournamentById(
			@Validated TaskDirectortournament taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			TaskDirectortournament bean = infoService.selectTaskById(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/editTaskDirectortournament")
	public String editTaskDirectortournament(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,
			@Validated TaskDirectortournament taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			if (file.size()>0) {
				
				TaskDirectortournament bean1 = infoService.selectTaskById(taskBean);
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(bean1.getAttach());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			infoService.update(taskBean);
			} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskDirectortournament";
	}

	@RequestMapping(value = "/saveTaskDirectortournament")
	public String saveTaskDirectortournament(
			@RequestParam(value = "file", required = false) List<MultipartFile> file,
			@Validated TaskDirectortournament taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			if (file.size()>0) {
				
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(uuid);
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
				taskBean.setAttach(uuid);
				
			}
			infoService.insert(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskDirectortournament";
	}

	@RequestMapping(value = "/delTaskDirectortournament")
	@ResponseBody
	public Map delTaskDirectortournament(
			@Validated TaskDirectortournament taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTask(taskBean);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	//
	// 学科竞赛指导任务controller--------------------结束

	// 学科竞赛指导任务controller--------------------开始
	@RequestMapping(value = "/goTaskTutor")
	@ResponseBody
	public ModelAndView goTaskTutor(@Validated TaskTutor bean, String page,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("taskTutor");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setStudentName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<TaskTutor> list = infoService.selectAllTask(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService.selectTaskByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/selectTaskTutorById")
	@ResponseBody
	public Map selectTaskTutorById(@Validated TaskTutor taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			TaskTutor bean = infoService.selectTaskById(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/editTaskTutor")
	public String editTaskTutor(@Validated TaskTutor taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {

			infoService.update(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskTutor";
	}

	@RequestMapping(value = "/saveTaskTutor")
	public String saveTaskTutor(@Validated TaskTutor taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			taskBean.setAttach(uuid);
			infoService.insert(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskTutor";
	}

	@RequestMapping(value = "/delTaskTutor")
	@ResponseBody
	public Map delTaskTutor(@Validated TaskTutor taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTask(taskBean);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	//
	// 学科竞赛指导任务controller--------------------结束

	// 发表文献controller--------------------开始
	@RequestMapping(value = "/goProjectPublish")
	@ResponseBody
	public ModelAndView goProjectPublish(@Validated ProjectPublish bean,
			String page, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("projectPublish");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			//时间
			if (bean.getPublishTime() != null ) {
				Calendar c = Calendar.getInstance();
				c.setTime( bean.getPublishTime());
				int searchTime = c.get(Calendar.YEAR);
				modelAndView.addObject("searchTime", searchTime);
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<ProjectPublish> list = infoService.selectAllTask(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService.selectTaskByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/selectProjectPublishById")
	@ResponseBody
	public Map selectProjectPublishById(@Validated ProjectPublish taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			ProjectPublish bean = infoService.selectTaskById(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/editProjectPublish")
	public String editProjectPublish(@Validated ProjectPublish taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {

			infoService.update(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectPublish";
	}

	@RequestMapping(value = "/saveProjectPublish")
	public String saveProjectPublish(@Validated ProjectPublish taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			taskBean.setAttach(uuid);
			infoService.insert(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectPublish";
	}

	@RequestMapping(value = "/delProjectPublish")
	@ResponseBody
	public Map delProjectPublish(@Validated ProjectPublish taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTask(taskBean);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	//
	// 发表文献controller--------------------结束

	// 人才工程项目controller--------------------开始

	@RequestMapping(value = "/goProjectPerson")
	@ResponseBody
	public ModelAndView goProjectPerson(@Validated ProjectPerson bean,
			String page, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("projectPerson");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			if (bean.getTime() != null ) {
				Calendar c = Calendar.getInstance();
				c.setTime( bean.getTime());
				int searchTime = c.get(Calendar.YEAR);
				modelAndView.addObject("searchTime", searchTime);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<ProjectPerson> list = infoService.selectAllTask(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService.selectTaskByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/selectProjectPersonById")
	@ResponseBody
	public Map selectProjectPersonById(@Validated ProjectPerson taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			ProjectPerson bean = infoService.selectTaskById(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/editProjectPerson")
	public String editProjectPerson(@Validated ProjectPerson taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {

			infoService.update(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectPerson";
	}

	@RequestMapping(value = "/saveProjectPerson")
	public String saveProjectPerson(@Validated ProjectPerson taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			taskBean.setAttach(uuid);
			infoService.insert(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectPerson";
	}

	@RequestMapping(value = "/delProjectPerson")
	@ResponseBody
	public Map delProjectPerson(@Validated ProjectPerson taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTask(taskBean);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	//
	// 人才工程项目controller--------------------结束

	// 学术讲座论坛项目controller--------------------开始
	@RequestMapping(value = "/goProjectLecture")
	@ResponseBody
	public ModelAndView goProjectLecture(
			@Validated ProjectLecture bean, String page,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("projectLecture");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			if (bean.getTime() != null ) {
				Calendar c = Calendar.getInstance();
				c.setTime( bean.getTime());
				int searchTime = c.get(Calendar.YEAR);
				modelAndView.addObject("searchTime", searchTime);
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<ProjectLecture> list = infoService.selectAllTask(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService.selectTaskByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/selectProjectLectureById")
	@ResponseBody
	public Map selectProjectLectureById(
			@Validated ProjectLecture taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			ProjectLecture bean = infoService.selectTaskById(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = "/editProjectLecture")
	public String editProjectLecture(
			@Validated ProjectLecture taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		try {

			infoService.update(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectLecture";
	}

	@RequestMapping(value = "/saveProjectLecture")
	public String saveProjectLecture(
			@Validated ProjectLecture taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		try {
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			taskBean.setAttach(uuid);
			infoService.insert(taskBean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectLecture";
	}

	@RequestMapping(value = "/delProjectLecture")
	@ResponseBody
	public Map delProjectLecture(
			@Validated ProjectLecture taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTask(taskBean);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}

	//
	// 服务社会项目controller--------------------结束
	
	
	// 服务社会项目controller--------------------开始
		@RequestMapping(value = "/goProjectSocialservice")
		@ResponseBody
		public ModelAndView goProjectSocialservice(
				@Validated ProjectSocialservice bean, String page,
				HttpServletRequest req, HttpServletResponse res) throws Exception {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("projectSocialservice");
			try {
				String parameter = req.getParameter("name");
				if (StringUtils.isNotBlank(parameter)) {
					String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
					bean.setName(keyword);
					modelAndView.addObject("searchName", keyword);
				}
				if (bean.gettName() != null && bean.gettName() != "") {
					modelAndView.addObject("searchTName", bean.gettName());
				}
				if (bean.getStartTime() != null ) {
					Calendar c = Calendar.getInstance();
					c.setTime( bean.getStartTime());
					int searchTime = c.get(Calendar.YEAR);
					modelAndView.addObject("searchTime", searchTime);
				}

				Teacher teacher = (Teacher) req.getSession().getAttribute(
						"userinfo");
				if (req.getSession().getAttribute("openAuthor") == "false"
						|| teacher.getAuthorlever() == 2) {
					int tId = teacher.gettId();
					bean.settId(tId);
				}
				// 每页显示条数
				int pageSize = Integer.parseInt(myconfig.get("pageSize"));

				List<ProjectSocialservice> list = infoService.selectAllTask(bean);
				modelAndView.addObject("userNum", list.size());

				// 总页数
				int pageTimes;
				if (list.size() > pageSize) {
					if (list.size() % pageSize == 0) {
						pageTimes = list.size() / pageSize;
					} else {
						pageTimes = list.size() / pageSize + 1;
					}
				} else {
					pageTimes = 1;
				}

				modelAndView.addObject("pageTimes", pageTimes);
				// 页面初始的时候page没有值
				if (null == page) {
					page = "1";
				}

				int startRow = (Integer.parseInt(page) - 1) * pageSize;
				list = infoService.selectTaskByPage(bean, startRow, pageSize);

				modelAndView.addObject("currentPage", Integer.parseInt(page));
				modelAndView.addObject("list", list);

			} catch (Exception e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}

			return modelAndView;
		}

		@RequestMapping(value = "/selectProjectSocialserviceById")
		@ResponseBody
		public Map selectProjectSocialserviceById(
				@Validated ProjectSocialservice taskBean,
				BindingResult bindingResult, HttpServletRequest req,
				HttpServletResponse res) throws Exception {
			Map data = new HashMap();
			data.put("errcode", "-1");
			data.put("errmsg", "失败");
			try {
				ProjectSocialservice bean = infoService.selectTaskById(taskBean);
				data.put("errcode", "0");
				data.put("errmsg", "成功");
				data.put("data", bean);
			} catch (Exception e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			return data;
		}

		@RequestMapping(value = "/editProjectSocialservice")
		public String editProjectSocialservice(
				@Validated ProjectSocialservice taskBean,
				BindingResult bindingResult, HttpServletRequest req,
				HttpServletResponse res) throws Exception {

			try {

				infoService.update(taskBean);
			} catch (Exception e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			return "redirect:/goProjectSocialservice";
		}

		@RequestMapping(value = "/saveProjectSocialservice")
		public String saveProjectSocialservice(
				@Validated ProjectSocialservice taskBean,
				BindingResult bindingResult, HttpServletRequest req,
				HttpServletResponse res) throws Exception {
			try {
				String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
				taskBean.setAttach(uuid);
				infoService.insert(taskBean);
			} catch (Exception e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			return "redirect:/goProjectSocialservice";
		}

		@RequestMapping(value = "/delProjectSocialservice")
		@ResponseBody
		public Map delProjectSocialservice(
				@Validated ProjectSocialservice taskBean,
				BindingResult bindingResult, HttpServletRequest req,
				HttpServletResponse res) throws Exception {
			Map data = new HashMap();
			data.put("errcode", "-1");
			data.put("errmsg", "失败");
			try {
				infoService.delTask(taskBean);
				data.put("errcode", "0");
			} catch (Exception e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			return data;
		}

		//
		// 学术讲座论坛项目controller--------------------结束
		@RequestMapping(value = "/TaskTeachingExcel")
		@ResponseBody
		public void TaskTeachingExcel(@Validated TaskTeaching bean,
				HttpServletRequest req, HttpServletResponse resp)
				throws IOException {

			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setName(keyword);
				req.getSession().setAttribute("searchName", keyword);
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
			
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}

			List<TaskTeaching> resultList = infoService.selectAllTaskTeaching(bean);

			resp.reset();
			OutputStream out = null;
			String strTitleName = "教学任务表";
			String strSheetName = "教学任务表";
			String filename = "教学任务表.xls";

			List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
			HttpSession session=req.getSession();
			if(session.getAttribute("openAuthor") == "true"){
				item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
			}
			item.add(new ExportExcelParam("课程名称", "name", 20 * 256));
			item.add(new ExportExcelParam("课程所属专业", "major", 20 * 256));
			item.add(new ExportExcelParam("课程性质", "property", 20 * 256));
			item.add(new ExportExcelParam("课时", "hour", 20 * 256));
			item.add(new ExportExcelParam("人数", "countMan", 20 * 256));
			item.add(new ExportExcelParam("考核方式", "assessmentMethod", 20 * 256));
			item.add(new ExportExcelParam("所属教学改革课题", "teachingSubject", 20 * 256));

			try {
				out = resp.getOutputStream();
				HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
						strTitleName, strSheetName);

				resp.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(filename, "UTF-8"));
				resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

				wb.write(out);

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (java.lang.reflect.InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.flush();
			out.close();

		}
		
		
	@RequestMapping(value = "/TaskCompanyExcel")
	@ResponseBody
	public void TaskCompanyExcel(@Validated TaskCompany bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<TaskCompany> resultList = infoService.selectAllTaskCompany(bean);

		resp.reset();
		OutputStream out = null;
		String strTitleName = "校企合作任务表";
		String strSheetName = "校企合作任务表";
		String filename = "校企合作任务表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		HttpSession session=req.getSession();
		if(session.getAttribute("openAuthor") == "true"){
			item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
		}
		item.add(new ExportExcelParam("企业名称", "name", 20 * 256));
		item.add(new ExportExcelParam("电话", "phone", 20 * 256));
		item.add(new ExportExcelParam("地点", "place", 20 * 256));

		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/TaskGraduationExcel")
	@ResponseBody
	public void TaskGraduationExcel(@Validated TaskGraduation bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<TaskGraduation> resultList = infoService.selectAllTaskCompany(bean);
		
		for(int i=0;i<resultList.size();i++){
			if(resultList.get(i).getIsPublic().equals("1")){
				resultList.get(i).setIsPublic("是");
			}else{
				resultList.get(i).setIsPublic("否");
			}
		}
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "毕业综合实践项目表";
		String strSheetName = "毕业综合实践项目表";
		String filename = "毕业综合实践项目表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		HttpSession session=req.getSession();
		if(session.getAttribute("openAuthor") == "true"){
			item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
		}
		item.add(new ExportExcelParam("设计或者论文名称", "name", 20 * 256));
		item.add(new ExportExcelParam("学生姓名", "studentName", 20 * 256));
		item.add(new ExportExcelParam("是否发布", "isPublic", 20 * 256));
		item.add(new ExportExcelParam("发表的刊物名称", "publicationName", 20 * 256));

		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/TaskDirectortournamentExcel")
	@ResponseBody
	public void TaskDirectortournamentExcel(@Validated TaskDirectortournament bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<TaskDirectortournament> resultList = infoService.selectAllTask(bean);
		
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "学科竞赛指导任务表";
		String strSheetName = "学科竞赛指导任务表";
		String filename = "学科竞赛指导任务表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		
		item.add(new ExportExcelParam("项目名", "name", 20 * 256));
		item.add(new ExportExcelParam("指导老师姓名", "tName", 20 * 256));
		item.add(new ExportExcelParam("学生姓名", "studentName", 20 * 256));
		item.add(new ExportExcelParam("获奖荣誉", "attach", 20 * 256));


		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/TaskTutorExcel")
	@ResponseBody
	public void TaskTutorExcel(@Validated TaskTutor bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setStudentName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<TaskTutor> resultList = infoService.selectAllTask(bean);
		
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "学业导师任务表";
		String strSheetName = "学业导师任务表";
		String filename = "学业导师任务表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		
		item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
		item.add(new ExportExcelParam("学生姓名", "studentName", 20 * 256));
		item.add(new ExportExcelParam("班级", "studentClass", 20 * 256));
		item.add(new ExportExcelParam("学生专业", "major", 20 * 256));

		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/ProjectPublishExcel")
	@ResponseBody
	public void ProjectPublishExcel(@Validated ProjectPublish bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<ProjectPublish> resultList = infoService.selectAllTask(bean);
		
		for(int i=0;i<resultList.size();i++){
			if(resultList.get(i).getType().equals("1")){
				resultList.get(i).setType("论文");
			}else{
				resultList.get(i).setType("著作教材");
			}
		}
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "发表文献表";
		String strSheetName = "发表文献表";
		String filename = "发表文献表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		HttpSession session=req.getSession();
		if(session.getAttribute("openAuthor") == "true"){
			item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
		}
		item.add(new ExportExcelParam("刊物名称", "name", 20 * 256));
		item.add(new ExportExcelParam("发表刊物", "pressCompany", 20 * 256));
		item.add(new ExportExcelParam("发表时间", "publishTime", 20 * 256));
		item.add(new ExportExcelParam("出版物编号", "issn", 20 * 256));
		item.add(new ExportExcelParam("类型", "type", 20 * 256));

		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/ProjectPersonExcel")
	@ResponseBody
	public void ProjectPersonExcel(@Validated ProjectPerson bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<ProjectPerson> resultList = infoService.selectAllTask(bean);
		
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "人才工程项目表";
		String strSheetName = "人才工程项目表";
		String filename = "人才工程项目表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		HttpSession session=req.getSession();
		
		item.add(new ExportExcelParam("人才工程名称", "name", 20 * 256));
		item.add(new ExportExcelParam("指导老师姓名", "tName", 20 * 256));
		item.add(new ExportExcelParam("学生姓名", "studentName", 20 * 256));
		item.add(new ExportExcelParam("人才工程开始时间", "time", 20 * 256));

		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	
	@RequestMapping(value = "/ProjectSocialserviceExcel")
	@ResponseBody
	public void ProjectSocialserviceExcel(@Validated ProjectSocialservice bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<ProjectSocialservice> resultList = infoService.selectAllTask(bean);
		
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "服务社会成果表";
		String strSheetName = "服务社会成果表";
		String filename = "服务社会成果表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		HttpSession session=req.getSession();
		if(session.getAttribute("openAuthor") == "true"){
			item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
		}
		item.add(new ExportExcelParam("服务企业名称或部门", "companyName", 20 * 256));
		item.add(new ExportExcelParam("成员", "member", 20 * 256));
		item.add(new ExportExcelParam("项目名称", "name", 20 * 256));
		item.add(new ExportExcelParam("服务时限", "serviceTime", 20 * 256));
		item.add(new ExportExcelParam("经费", "money", 20 * 256));
		item.add(new ExportExcelParam("立项时间", "startTime", 20 * 256));

		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/ProjectLectureExcel")
	@ResponseBody
	public void ProjectLectureExcel(@Validated ProjectLecture bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setName(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<ProjectLecture> resultList = infoService.selectAllTask(bean);
		
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "学术讲座论坛表";
		String strSheetName = "学术讲座论坛表";
		String filename = "学术讲座论坛表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		
		
		item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
		item.add(new ExportExcelParam("学术讲座名称", "name", 20 * 256));
		item.add(new ExportExcelParam("成员", "academics", 20 * 256));
		item.add(new ExportExcelParam("项目名称", "time", 20 * 256));
		
		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/TeacherListExcel")
	@ResponseBody
	public void TeacherListExcel(@Validated Teacher bean,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String parameter = req.getParameter("name");
		if (StringUtils.isNotBlank(parameter)) {
			String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
			bean.setMajor(keyword);
			req.getSession().setAttribute("searchName", keyword);
		}
		Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
		
		if (req.getSession().getAttribute("openAuthor") == "false"
				|| teacher.getAuthorlever() == 2) {
			int tId = teacher.gettId();
			bean.settId(tId);
		}

		List<Teacher> resultList = infoService.selectAllTask(bean);
		
		resp.reset();
		OutputStream out = null;
		String strTitleName = "教师表";
		String strSheetName = "教师表";
		String filename = "教师表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();
		
		
		item.add(new ExportExcelParam("教师姓名", "tName", 20 * 256));
		item.add(new ExportExcelParam("学历", "education", 20 * 256));
		item.add(new ExportExcelParam("职称", "title", 20 * 256));
		item.add(new ExportExcelParam("性别", "sex", 20 * 256));
		item.add(new ExportExcelParam("专业", "major", 20 * 256));
		
		try {
			out = resp.getOutputStream();
			HSSFWorkbook wb = ExportExcelUtil.generateExcel(resultList, item,
					strTitleName, strSheetName);

			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");

			wb.write(out);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();
		out.close();

	}

	@RequestMapping(value = "/selectAllTeacher")
	@ResponseBody
	public Map selectAllTeacher(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			List<Teacher> list = infoService.selectAllTeacher();
			req.getSession().setAttribute("teacherList", list);
			data.put("teacherList", list);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/goShowAllTeacherInfoPage")
	@ResponseBody
	public ModelAndView goShowAllTeacherInfoPage(
			@Validated Teacher bean, String page,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("teacherInfoList");
		try {
			String parameter = req.getParameter("name");
			if (StringUtils.isNotBlank(parameter)) {
				String keyword = java.net.URLDecoder.decode(parameter, "UTF-8");
				bean.setMajor(keyword);
				modelAndView.addObject("searchName", keyword);
			}
			if (bean.gettName() != null && bean.gettName() != "") {
				modelAndView.addObject("searchTName", bean.gettName());
			}
			//时间
			if (bean.getSchoolYear() != null ) {
				Calendar c = Calendar.getInstance();
				c.setTime( bean.getSchoolYear());
				int searchTime = c.get(Calendar.YEAR);
				modelAndView.addObject("searchTime", searchTime);
			}
			Teacher teacher = (Teacher) req.getSession().getAttribute(
					"userinfo");
			if (req.getSession().getAttribute("openAuthor") == "false"
					|| teacher.getAuthorlever() == 2) {
				int tId = teacher.gettId();
				bean.settId(tId);
			}
			// 每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));

			List<Teacher> list = infoService.selectAllTask(bean);
			modelAndView.addObject("userNum", list.size());

			// 总页数
			int pageTimes;
			if (list.size() > pageSize) {
				if (list.size() % pageSize == 0) {
					pageTimes = list.size() / pageSize;
				} else {
					pageTimes = list.size() / pageSize + 1;
				}
			} else {
				pageTimes = 1;
			}

			modelAndView.addObject("pageTimes", pageTimes);
			// 页面初始的时候page没有值
			if (null == page) {
				page = "1";
			}

			int startRow = (Integer.parseInt(page) - 1) * pageSize;
			list = infoService.selectTaskByPage(bean, startRow, pageSize);

			modelAndView.addObject("currentPage", Integer.parseInt(page));
			modelAndView.addObject("list", list);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/selectTeacherById")
	@ResponseBody
	public Map selectTeacherById(
			@Validated Teacher taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			Teacher bean = infoService.selectTaskById(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "成功");
			data.put("data", bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/delTeacher")
	@ResponseBody
	public Map delTeacher(
			@Validated Teacher taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTask(taskBean);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	public Map resetPwd(
			@Validated Teacher taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.update(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "修改密码成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}
	@RequestMapping(value = "/changeToAuthor")
	@ResponseBody
	public Map changeToAuthor(
			@Validated Teacher taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			taskBean.setAuthorlever(1);
			infoService.update(taskBean);
			data.put("errcode", "0");
			data.put("errmsg", "修改密码成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}
	@RequestMapping(value = "/selectAllAttach")
	@ResponseBody
	public Map selectAllAttach(
			@Validated Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			List<Attach> list = new ArrayList<Attach>();
			if(taskBean.getInstanceId()!=""&&taskBean.getInstanceId()!=null){
				list=infoService.selectAllTask(taskBean);
			}
			if(list.size()>0){
				req.getSession().setAttribute("listAttach", list);
			}else{
				req.getSession().removeAttribute("listAttach");
			}
			
			data.put("listAttach", list);
			data.put("errcode", "0");
			data.put("errmsg", "查询所有附件");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data;
	}
	@RequestMapping(value = "/uploadAttach1")
	public String uploadAttach1(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTeachingTask";
	}
	@RequestMapping(value = "/uploadAttach2")
	public String uploadAttach2(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskCompany";
	}
	@RequestMapping(value = "/uploadAttach3")
	public String uploadAttach3(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskGraduation";
	}
	@RequestMapping(value = "/uploadAttach4")
	public String uploadAttach4(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskDirectortournament";
	}
	@RequestMapping(value = "/uploadAttach5")
	public String uploadAttach5(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTaskTutor";
	}
	@RequestMapping(value = "/uploadAttach6")
	public String uploadAttach6(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectPublish";
	}
	@RequestMapping(value = "/uploadAttach7")
	public String uploadAttach7(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectPerson";
	}
	@RequestMapping(value = "/uploadAttach8")
	public String uploadAttach8(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectSocialservice";
	}
	@RequestMapping(value = "/uploadAttach9")
	public String uploadAttach9(
			@RequestParam(value = "file", required = false)  List<MultipartFile> file,Attach taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			String fileName;
			if (file.size()>0) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				for (int i = 0; i < file.size(); i++) {
					fileName = file.get(i).getOriginalFilename();
					File dir = new File(path, fileName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// MultipartFile自带的解析方法
					file.get(i).transferTo(dir);
					Attach bean = new Attach();
					bean.setInstanceId(taskBean.getInstanceId());
					bean.setFileName(file.get(i).getOriginalFilename());
					bean.setType(file.get(i).getContentType());
					infoService.insert(bean);
				}
			}
			data.put("errcode", "0");
			data.put("errmsg", "附件上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goProjectLecture";
	}
}
