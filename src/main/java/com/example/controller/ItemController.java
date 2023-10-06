package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;
	
	// コンストラクタでserviceをインジェクション
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
    
	// 商品一覧表示
	@GetMapping
	public String index(Model model) {
		List<Item> items = this.itemService.findAll();
		// htmlのほうで利用する変数としてitemsをセット
		model.addAttribute("items", items);
		// 商品一覧を表示
		return "item/index";
	}
	
	
	// 商品登録ページ表示
	@GetMapping("toroku")
	public String torokuPage(@ModelAttribute("itemForm") ItemForm itemForm) {
		// 商品登録ページを表示
		return "item/torokuPage";
	}
	
	
	// 商品登録実行
	@PostMapping("toroku")
	public String toroku(ItemForm itemForm) {
		this.itemService.save(itemForm);
		return "redirect:/item";
	}
	
	
	// 商品編集処理ページ表示
	@GetMapping("hensyu/{id}")
	public String hensyuPage(@PathVariable("id") Integer id, Model model
			                  ,@ModelAttribute("itemForm") ItemForm itemForm) {
		// Entityクラスのインスタンスをidより検索し取得
				Item item = this.itemService.findById(id);
				// DTOクラスのフィールドにセット
				itemForm.setName(item.getName());
				itemForm.setPrice(item.getPrice());
				// idをセット
				model.addAttribute("id", id);
		return "item/hensyuPage";
	}
	
	
	// 商品編集実行
	@PostMapping("hensyu/{id}")
	public String hensyu(@PathVariable("id") Integer id, ItemForm itemForm) {
		this.itemService.update(id, itemForm);
		return "redirect:/item";
	}
	
	
	// 商品削除実行
	@PostMapping("sakujyo/{id}")
	public String sakujyo(@PathVariable("id") Integer id) {
		this.itemService.delete(id);
		return "redirect:/item";
	}
}
