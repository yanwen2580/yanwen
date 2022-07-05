package cn.itrip.trade.sdk.alipay;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2021000119665711";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSWcrvPa5cVffoY2ljKHvcALk+/QxeUWZfneWQ+mfm3v8rsiTZk0ya+3JxrkoElhlW5MT4kslzJYw2HaBwmM19X+rnpVOM3pco2g75WjpWEFo4PY+XQPfrUDLRrlGZOL8q4zG5wf1INH7uhlP6uOCskWxQmszotY6OOZP0LiDwitp7Lq3yiQ3GnqofOFUnJ7yiIptUh6qRJd1YN2ntosQ3foMmN5JdxqcHY/Vq4E381huLHwSRwS5RR1VmdfeCUBcLfEvWZrxmpRxsw/NCMJEdZYBZKMmMcUBbSaeWCvxITdKeHQoDXQZZ0yuyupqTKy4bhoYLvtrTkP6cOR0F1SVJAgMBAAECggEABJ/vyMWjN+nDjsVtdCO4XkhrkjD+7hAWcxkTQxSWqfYMDD5x6ao89nzRrgP69NAldth93PkOgqQCHUesZUJ+4+JfEvjnfgEet7zEUl7mjwevQi231ujHrNj48iVXEMd5Swqo7JzTuy9lf3YhO0ASYYGq8ZUiuovaOGZbZZD7m35Lk0xv6MXrHfy2O1pCXrp+m2IWUMtwKIgA74ACHjnXPdO0T+p6TPwxE+Hz2l2QDeZruB/aWWH9FKoP0cWjn8pEKtKt1kkF4FO3g7qmKcIyhMYHzirag6KA8UiiAqr0WvWZleC9fULRIjuuorgxQXPIPh4zuaKov7hwoZBA8FbTcQKBgQDDVyrMAJph0NGx7fdq8GKXKLu07MGDoRqTdMX9aOO3kl908+DJWToK8apfp4TzzSbwR9ciUIJrPQLSgtMbX3lGVBvxFD4B+aADYBCeDH8/uJof6OtPD9CjmJ/PC1COANo0HjdlTAHjryT2qSo2TaqR+EfXVND52+O5sdUDkg5DzQKBgQC/zCAp/Bb7k+vYaox52jOdJUKYa8KSux5EYyQXEueo7m4GtAiiw7c/fY4bOXpzWIqA278798csEYfR5c3zkbuAmtdPo0YBFqpi6hGpdmuws7bowf1dAcFMUL7ydpV3zs/jyWfihaBPKSh5t0KE8uzO1LJHZSygRYdmiZ4C4bJjbQKBgGl4EgJtvO1daysvuiwCOKk29Ape3qksfgXpwdDuJi2CmYmy98afJ4irLPtSnqVoOkL39v9vCsvN+E0jF8qqxPclCKUk8cntNuk7oB1F0BremFDsWMiqWFNMX9TRXiAd87K6Mdy+qjJ96W5IriL90j/YFyn7gI23nQAxNTslTBBtAoGBAIQpvOAxfgTN4W63vSUaXwaibklpuCI0Hp8xUeU0eb3Gzrp/xJOkGdn+QrYv3VkuYgJ5akWAcBbF3bmdiVWVzobILCYMdGi7nZ2bfaAwofSZYijxxA0a8IAOBAZAAK8WK9rjJlrJibYCvolg5s2GN/guSMs5xI6MWskle2Sw8aPhAoGAK7wC3DkAOTr5iW3l1zA9/q4E+4sSDz3g9lmzEW5iczfvswaJnf4pk/w9dDzDxFeDwlrF1VF9UB18Jimyj7Ma69+OYr0YV7NXRcWztaXxjIsfUHC4GWeF9h35RBeKGHyBD8T7hkLD8X1n7fTo51showTjdrlzRG9AM/NaEO+5DTI=";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://ma4qcw.natappfree.cc/trade/api/prepay/notify_url";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://ma4qcw.natappfree.cc /trade/api/prepay/notify_url";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr679Toqnjjw4NrEirun5fjM8tBkBj2yDSeWD6zIJIFSJWk3dJQ6wa2HMU1ZLNfnNi0ak1SDLDGkQbYjKsecjgPqr+Kmgnel84+fJnqSywbytza/EKrbT0BfBY5hGkkOHPyFflAfZTHUbKOLJSYo6Upzfl58XLGb+9agynI09aKDWqogznGxo+TTu6xwDKFp+gXBoj7e2CSQrf2n7QzDvbMUdNkxzC45XSrcXQSc0HcltFcO7XIskvcGLBWKd0r+E5EfWfSTSPPwgbdcY0XzzM84lYBUhcvCq7TqmqXxUvt8XHUxYMdMyFjC5HHQPWEdF56rTfQNwEhRgWf2Y/WxtwwIDAQAB";
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
