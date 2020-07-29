package com.tengke.supermarket.controller;

import com.tengke.supermarket.dto.PageDTO;
import com.tengke.supermarket.dto.ResultDTO;
import com.tengke.supermarket.model.Goods;
import com.tengke.supermarket.model.PurchaseRecord;
import com.tengke.supermarket.model.SellItem;
import com.tengke.supermarket.model.SellRecord;
import com.tengke.supermarket.service.GoodsService;
import com.tengke.supermarket.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 彤老板
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页展示商品，供员工选取进货商品
     * @param pageNo 页码
     * @param size 页面大小
     * @return 商品信息
     */
    @GetMapping("/goods/{pageNo}/{size}")
    public ResultDTO showGoods(@PathVariable("pageNo") int pageNo, @PathVariable("size") int size) {
        return goodsService.showGoodsList(pageNo,size);
    }

    /**
     * 按商品编号搜索商品
     * @param gdsId 商品编号
     * @return 商品信息
     */
    @GetMapping("/goods/search/{id}")
    public ResultDTO searchGoods(@PathVariable("id") int gdsId) {
        return goodsService.searchGoodsById(gdsId);
    }

    /**
     * 进货，新增进货记录
     * @param purchaseRecord 一条进货记录
     * @return 提示消息
     */
    @PostMapping("/addPurchase/{purchaseRecord}")
    public String addPurchaseRecord(@PathVariable("purchaseRecord") PurchaseRecord purchaseRecord) {
        return purService.purchase(purchaseRecord);
    }

    /**
     * 分页展示进货记录
     * @return 进货记录列表，List存储
     */
    @GetMapping("/allPurRecord/{pageNo}/{size}")
    public PageDTO<PurchaseRecord> getPurRecordsByPage(@PathVariable("pageNo") int page, @PathVariable("size") int size) {
        return purService.showPurRecordByPage(page, size);
    }

    /**
     * 按照进货编号查询进货记录
     * @param purchaseId 进货记录编号
     * @return 一条对应进货编号的进货记录，List存储
     */
    @GetMapping("/PurRecordById/{id}")
    public List<PurchaseRecord> getPurRecordById(@PathVariable("id") int purchaseId) {
        return purService.showPurRecordById(purchaseId);
    }


}
