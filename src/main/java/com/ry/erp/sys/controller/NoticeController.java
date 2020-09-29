package com.ry.erp.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.domain.Notice;
import com.ry.erp.sys.service.NoticeService;
import com.ry.erp.sys.vo.NoticeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
