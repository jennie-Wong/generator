

package com.example.generator.utils;
import java.util.HashMap;

/**
 * 返回数据
 *
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 200);
		put("msg", "success");
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}


	public static R ok(Object obj) {
		R r = new R();
		r.put("data",obj);
		return r;
	}


	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
}
