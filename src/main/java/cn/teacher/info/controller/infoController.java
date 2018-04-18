package cn.teacher.info.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			taskTeaching.setCode(uuid);
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
			@RequestParam(value = "file", required = false) MultipartFile file,
			@Validated TaskDirectortournament taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			if (!file.isEmpty()) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				fileName = file.getOriginalFilename();
				File dir = new File(path, fileName);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// MultipartFile自带的解析方法
				file.transferTo(dir);
				taskBean.setAttach(fileName);
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
			@RequestParam(value = "file", required = false) MultipartFile file,
			@Validated TaskDirectortournament taskBean,
			BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			if (!file.isEmpty()) {
				String path = myconfig.get("pic_download"); // request.getSession().getServletContext().getRealPath("upload");
				fileName = file.getOriginalFilename();
				File dir = new File(path, fileName);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// MultipartFile自带的解析方法
				file.transferTo(dir);
				taskBean.setAttach(fileName);
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
		int tId = teacher.gettId();
		bean.settId(tId);

		List<TaskCompany> resultList = infoService.selectAllTaskCompany(bean);

		resp.reset();
		OutputStream out = null;
		String strTitleName = "校企合作任务表";
		String strSheetName = "校企合作任务表";
		String filename = "校企合作任务表.xls";

		List<ExportExcelParam> item = new ArrayList<ExportExcelParam>();

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

}
