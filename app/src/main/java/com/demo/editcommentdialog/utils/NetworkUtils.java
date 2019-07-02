package com.demo.editcommentdialog.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class NetworkUtils {

	private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
	private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
	private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");


	public static boolean isIPv4Address(String input) {
		return IPV4_PATTERN.matcher(input).matches();
	}

	public static boolean isIPv6StdAddress(String input) {
		return IPV6_STD_PATTERN.matcher(input).matches();
	}
	/**
	 * 检测网络连接是否可用
	 *
	 * @param ctx
	 * @return true 可用; false 不可用
	 */
	public synchronized static boolean isNetworkAvailable(Context ctx) {
		if (ctx != null) {
			ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm == null) {
				return false;
			}
			try {
				NetworkInfo[] netinfo = cm.getAllNetworkInfo();
				if (netinfo == null) {
					return false;
				}
				for (NetworkInfo element : netinfo) {
					if (element.isConnected()) {
						return true;
					}
				}
			} catch (Exception ex) {
				Log.e("net",ex.getMessage());
			}
		}
		return false;
	}

	/**
	 * Get IP address from first non-localhost interface
	 *
	 * @return address or empty string
	 */
	private static String IPADDRESS_CACHE;
	public static String getIPAddressFromCache(boolean useIPv4){
		try {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
				for (InetAddress addr : addrs) {
					if (!addr.isLoopbackAddress()) {
						String sAddr = addr.getHostAddress().toUpperCase();
						boolean isIPv4 = isIPv4Address(sAddr);
						if (useIPv4) {
							if (isIPv4)
								return sAddr;
						} else {
							if (!isIPv4) {
								int delim = sAddr.indexOf('%'); // drop ip6 port
								// suffix
								return delim < 0 ? sAddr : sAddr.substring(0, delim);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
		} // for now eat exceptions
		return "";
	}

	public static String getIPAddress(boolean useIPv4) {
		if(TextUtils.isEmpty(IPADDRESS_CACHE)){
			IPADDRESS_CACHE = getIPAddressFromCache(useIPv4);
		}
		return IPADDRESS_CACHE;
	}

	public static int getNetwork(Context context) {
		if (isWifiAvailable(context)) {
			return -1;
		} else {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if (tm != null) {
				return tm.getNetworkType();
			}

		}

		return TelephonyManager.NETWORK_TYPE_UNKNOWN;
	}

	/**
	 * 是否为wifi链接
	 * @return
	 */
	public static boolean isWifiAvailable(Context ctx) {
		try {
			if (ctx != null) {
				ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
				return manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
			}
		}catch (Exception e){

		}

		return false;
	}

	public static String getNetworkTypeName(Context context) {
		if (context != null) {
			try {
				ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
				if (connectMgr != null) {
					NetworkInfo info = connectMgr.getActiveNetworkInfo();
					if (info != null) {
						switch (info.getType()) {
							case ConnectivityManager.TYPE_WIFI:
								return "WIFI";
							case ConnectivityManager.TYPE_MOBILE:
								return getNetworkTypeName(info.getSubtype());
						}
					}
				}
			} catch (Exception e) {
			}
		}
		return getNetworkTypeName(TelephonyManager.NETWORK_TYPE_UNKNOWN);
	}

	public static String getNetworkTypeName(int type) {
		switch (type) {
			case TelephonyManager.NETWORK_TYPE_GPRS:
				return "GPRS";
			case TelephonyManager.NETWORK_TYPE_EDGE:
				return "EDGE";
			case TelephonyManager.NETWORK_TYPE_UMTS:
				return "UMTS";
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				return "HSDPA";
			case TelephonyManager.NETWORK_TYPE_HSUPA:
				return "HSUPA";
			case TelephonyManager.NETWORK_TYPE_HSPA:
				return "HSPA";
			case TelephonyManager.NETWORK_TYPE_CDMA:
				return "CDMA";
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
				return "CDMA - EvDo rev. 0";
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
				return "CDMA - EvDo rev. A";
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
				return "CDMA - EvDo rev. B";
			case TelephonyManager.NETWORK_TYPE_1xRTT:
				return "CDMA - 1xRTT";
			case TelephonyManager.NETWORK_TYPE_LTE:
				return "LTE";
			case TelephonyManager.NETWORK_TYPE_EHRPD:
				return "CDMA - eHRPD";
			case TelephonyManager.NETWORK_TYPE_IDEN:
				return "iDEN";
			case TelephonyManager.NETWORK_TYPE_HSPAP:
				return "HSPA+";
			default:
				return "UNKNOWN";
		}
	}
}
