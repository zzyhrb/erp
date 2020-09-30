package com.ry.erp.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.common.ResultObj;
import com.ry.erp.sys.common.WebUtils;
import com.ry.erp.sys.domain.Notice;
import com.ry.erp.sys.domain.User;
import com.ry.erp.sys.service.NoticeService;
import com.ry.erp.sys.vo.NoticeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询
     */
    @RequestMapping("loadAllNotice")
    public DataGridView loadAllNotice(NoticeVO noticeVO){
        IPage<Notice> page =new Page<>(noticeVO.getPage(),noticeVO.getLimit());
        QueryWrapper<Notice> queryWrapper =new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(noticeVO.getTitle()),"title",noticeVO.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(noticeVO.getOpername()),"opername",noticeVO.getOpername());
        queryWrapper.ge(noticeVO.getStartTime()!=null,"createtime",noticeVO.getStartTime());
        queryWrapper.le(noticeVO.getEndTime()!=null ,"createtime",noticeVO.getEndTime());
        queryWrapper.orderByDesc("createtime");
        this.noticeService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 根据用户id删除信息
     * @param id
     * @return
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(Integer id){
        try{
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;        }


    }

    /**
     * 批量删除
     * @param noticeVO
     * @return
     */
    @RequestMapping("batchNotice")
    public ResultObj batchNotice(NoticeVO noticeVO){
        try{
            Collection<Serializable> idList =new ArrayList<>();
            for (Integer id :noticeVO.getIds()){
                idList.add(id);
            }
            this.noticeService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;

        }



    }

    /**
     * 添加
     * @param noticeVO
     * @return
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(NoticeVO noticeVO){
        try{
            noticeVO.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            noticeVO.setOpername(user.getName());
            this.noticeService.save(noticeVO);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;

        }


    }

    /**
     * 修改
     * @param noticeVO
     * @return
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(NoticeVO noticeVO){
        try {
            this.noticeService.updateById(noticeVO);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
