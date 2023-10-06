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
		// データの疎通確認
		List<Item> items = this.itemService.findAll();
		// コンソールよりListの中身を確認する
		System.out.println(items.toString());
		return "item/index";
	}
	
	
	// 商品登録ページ表示
	@GetMapping("toroku")
	public String torokuPage(@ModelAttribute("itemform") ItemForm itemform) {
		// 処理を追加
		return "item/torokuPage";
	}
	
	
	// 商品登録実行
	@PostMapping("toroku")
	public String toroku(ItemForm itemform) {
		// 処理を追加
		return "redirect:/item";
	}
	
	
	// 商品編集処理ページ表示
	@GetMapping("hensyu/{id}")
	public String hensyuPage(@PathVariable("id") Integer id, Model model
			                  ,@ModelAttribute("itemform") ItemForm itemForm) {
		// 処理を追加
		return "item/hensyuPage";
	}
	
	
	// 商品編集実行
	@PostMapping("hensyu/{id}")
	public String hensyu(@PathVariable("id") Integer id
			              ,@ModelAttribute("itemform") ItemForm itemForm) {
		// 処理を追加
		return "redirect:/item";
	}
	
	
	// 商品削除実行
	@PostMapping("sakujyo/{id}")
	public String sakujyo(@PathVariable("id") Integer id) {
		// 処理を追加
		return "redirect:/item";
	}
}
