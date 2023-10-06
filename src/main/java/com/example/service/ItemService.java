package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.repository.ItemRepository;

@Service
public class ItemService {
    
	private final ItemRepository itemRepository;
	
	// コンストラクタでrepositoryをインジェクション
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	
	// データ全件取得
	public List<Item> findAll() {
		return this.itemRepository.findAll();
	}
	
	
	// DELETED_ATカラムがNULLのデータのみ取得
	public List<Item> findByDeletedAtIsNull() {
		return this.itemRepository.findByDeletedAtIsNull();
	}
	
	
	// データ保存用メソッド
	public Item save(ItemForm itemForm) {
		// entityクラスのインスタンス生成
		Item item = new Item();
		// DTOクラスからフィールドをセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		// saveメソッドでデータ保存
		return this.itemRepository.save(item);
	}
	
	
	// IDカラムを利用してデータ検索
	public Item findById(Integer id) {
		Optional<Item> optionalItem = this.itemRepository.findById(id);
		Item item = optionalItem.get();
		return item;
	}
	
	
	// データ更新用メソッド
	public Item update(Integer id, ItemForm itemForm) {
		// データ１件分のEntityクラスを取得
		Item item = this.findById(id);
		// DTOクラスからフィールドをセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		// saveメソッドでデータ保存
		return this.itemRepository.save(item);
	}
	
	
	// データ削除用メソッド(論理削除)
	public Item delete(Integer id) {
		// idから該当のEntityクラスを取得
		Item item = this.findById(id);
		// EntityクラスのdeletedAtフィールドを現在日時で上書き
		item.setDeletedAt(LocalDateTime.now());
		// 更新処理
		return this.itemRepository.save(item);
	}
}
