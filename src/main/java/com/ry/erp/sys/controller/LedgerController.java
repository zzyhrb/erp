package com.ry.erp.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.common.ResultObj;
import com.ry.erp.sys.domain.Ledger;
import com.ry.erp.sys.service.LedgerService;
import com.ry.erp.sys.vo.LedgerVo;
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
@RequestMapping("ledger")
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    /**
     * 查询
     * @param ledgerVo
     * @return
     */
    @RequestMapping("loadAllLedger")
    private DataGridView loadAllLedger(LedgerVo ledgerVo){
        IPage<Ledger> page =new Page<>(ledgerVo.getPage(),ledgerVo.getLimit());
        QueryWrapper<Ledger> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(ledgerVo.getName()),"name",ledgerVo.getName());
        queryWrapper.eq(ledgerVo.getStart()!=null ,"start",ledgerVo.getStart());
        queryWrapper.orderByDesc("money");
        this.ledgerService.page(page,queryWrapper);
        return new  DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加
     * @param ledgerVo
     * @return
     */
    @RequestMapping("addLedger")
    public ResultObj addLedger(LedgerVo ledgerVo){
        try{
            this.ledgerService.save(ledgerVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    /**
     * 修改
     * @param ledgerVo
     * @return
     */
    @RequestMapping("updateLedger")
    public ResultObj updateLedger(LedgerVo ledgerVo){
        try {
            this.ledgerService.updateById(ledgerVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     * @param ledgerVo
     * @return
     */
    @RequestMapping("deleteLedger")
    public ResultObj deleteLedger(LedgerVo ledgerVo){
        try {
            this.ledgerService.removeById(ledgerVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }

}
