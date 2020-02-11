package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}
	
	@RequestMapping(value={"/hello2", "/hello2/{name}"})
	public String hello2(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		} else {
			model.addAttribute("name", "Phoenix");
		}
		return "hello2";
	}
	
	@RequestMapping("/hitung/{angkasatu}/{angkadua}")
	public String hitung(@PathVariable Integer angkasatu, @PathVariable Integer angkadua, Model model) {
		model.addAttribute("angkasatu", angkasatu);
		model.addAttribute("angkadua", angkadua);
		Integer hasil;
		hasil = angkasatu + angkadua;
		model.addAttribute("hasil", hasil);
		String temp = convertTerbilang(hasil);
		model.addAttribute("huruf", temp);
		return "calcuconvert";
	}
	
	public static String convertTerbilang(int hasil) {
		String [] bilangan = {"","Satu","Dua","Tiga","Empat","Lima","Enam","Tujuh","Delapan","Sembilan","Sepuluh","sebelas"};
		String temp = "";
		if (hasil < 12) {
			temp = bilangan[hasil];
		}
		else if(hasil < 20) {
			temp = bilangan[hasil-10] +  " Belas ";
		}
		else if(hasil < 100) {
			temp = convertTerbilang(hasil/10) + " Puluh " + convertTerbilang(hasil%10);
		}
		else if(hasil < 200) {
			temp = " Seratus " + convertTerbilang(hasil%100);
		}
		else if(hasil < 1000) {
			temp = convertTerbilang(hasil/100) + " Ratus " + convertTerbilang(hasil%100);
		}
		else if(hasil < 2000) {
			temp = "seribu" + convertTerbilang(hasil%1000);
		}
		else if (hasil < 1000000) {
			temp = convertTerbilang(hasil/1000) + " ribu" + convertTerbilang(hasil%1000);
		}
		
		return temp;
	}
}
