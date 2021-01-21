package model.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Hideaki Yabe
 * 邂｡逅�閠�繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｨ郢九＄DAO繧ｯ繝ｩ繧ｹ縲�
 */
public class UserDAO {
	/**
	 * 蜚ｯ荳�縺ｮ繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ繧堤函謌舌☆繧�
	 */
	private static UserDAO instance = new UserDAO(); //蜚ｯ荳�縺ｮ繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ縺ｨ縺吶ｋ

	/**
	 * 迚ｹ螳壹�ｮ繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｨ縺ｮ謗･邯�(繧ｻ繝�繧ｷ繝ｧ繝ｳ)縲�
	 */
	private Connection con;
	/**
	 * 髱咏噪SQL譁�繧貞ｮ溯｡後＠縲∽ｽ懈�舌＆繧後◆邨先棡繧定ｿ斐☆縺溘ａ縺ｫ菴ｿ逕ｨ縺輔ｌ繧九が繝悶ず繧ｧ繧ｯ繝医��
	 */
	private Statement st;
	/**
	 * private縺ｮ縺溘ａ譁ｰ隕上�ｮ繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ繧偵▽縺上ｉ縺帙↑縺�縲�
	 */
	private UserDAO() {
	}

	/**
	 * @return UserDAO縺ｮ蜚ｯ荳�縺ｮ繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ縲�
	 * 蜚ｯ荳�縺ｮ繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ繧貞叙蠕励☆繧九��
	 */
	public static UserDAO getInstance() {
		return instance;
	}

	/**
	 * @throws SQLException 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ蜃ｦ逅�縺ｫ蝠城｡後′縺ゅ▲縺溷�ｴ蜷医��
	 * 迚ｹ螳壹�ｮ繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｨ縺ｮ謗･邯�(繧ｻ繝�繧ｷ繝ｧ繝ｳ)繧堤函謌舌☆繧九��
	 */
	public void dbConnect() throws SQLException {
		ConnectionManager cm = ConnectionManager.getInstance();
		con = cm.connect();
	}

	/**
	 * @throws SQLException 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ蜃ｦ逅�縺ｫ蝠城｡後′縺ゅ▲縺溷�ｴ蜷医��
	 * 髱咏噪SQL譁�繧貞ｮ溯｡後＠縲∽ｽ懈�舌＆繧後◆邨先棡繧定ｿ斐☆縺溘ａ縺ｫ菴ｿ逕ｨ縺輔ｌ繧九が繝悶ず繧ｧ繧ｯ繝医ｒ逕滓�舌☆繧九��
	 */
	public void createSt() throws SQLException {
		st = con.createStatement();
	}

	/**
	 * 迚ｹ螳壹�ｮ繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｨ縺ｮ謗･邯�(繧ｻ繝�繧ｷ繝ｧ繝ｳ)繧貞��譁ｭ縺吶ｋ縲�
	 */
	public void dbDiscon() {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param userId - 繝ｦ繝ｼ繧ｶ繝ｼID縲�
	 * @param password - 繝代せ繝ｯ繝ｼ繝峨��
	 * @return 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｨ荳�閾ｴ縺励※縺�縺溘ｉtrue縲∽ｸ�閾ｴ縺励※縺�縺ｪ縺九▲縺溘ｉfalse縲�
	 * @throws SQLException縲ゅョ繝ｼ繧ｿ繝吶�ｼ繧ｹ蜃ｦ逅�縺ｫ蝠城｡後′縺ゅ▲縺溷�ｴ蜷医��
	 * @throws NoSuchAlgorithmException縲ゅ≠繧区囓蜿ｷ繧｢繝ｫ繧ｴ繝ｪ繧ｺ繝�縺瑚ｦ∵ｱゅ＆繧後◆縺ｫ繧ゅ°縺九ｏ繧峨★縲∫樟蝨ｨ縺ｮ迺ｰ蠅�縺ｧ縺ｯ菴ｿ逕ｨ蜿ｯ閭ｽ縺ｧ縺ｪ縺�蝣ｴ蜷医��
	 * 謖�螳壹＆繧後◆employeeCode縺ｨpassword縺九ｉ邂｡逅�閠�繝ｦ繝ｼ繧ｶ繝ｼ縺後Ο繧ｰ繧､繝ｳ縺ｧ縺阪ｋ縺九←縺�縺九メ繧ｧ繝�繧ｯ縺吶ｋ縲�
	 */
	public boolean loginUser(String userId, String password) throws SQLException, NoSuchAlgorithmException {

		boolean loginUserChkFlag = false;

		//繝代せ繝ｯ繝ｼ繝峨ｒ繝上ャ繧ｷ繝･蛹�
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] passwordDigest = digest.digest(password.getBytes());
		String sha1 = String.format("%040x", new BigInteger(1, passwordDigest));

		// user_id縺ｨpassword縺後�槭ャ繝√＠縺溘Θ繝ｼ繧ｶ繝ｬ繧ｳ繝ｼ繝峨ｒ蜿門ｾ励☆繧�
		String sql = "select * from m_user where user_id='"
				+ userId + "' and password='" + sha1.substring(8) + "';";
		ResultSet rs = st.executeQuery(sql);

		// 繝槭ャ繝√＠縺溘ョ繝ｼ繧ｿ縺後≠繧後�ｰtrue繧剃ｻ｣蜈･縺吶ｋ
		if (rs.next()) {
			if (userId.equals(rs.getString(1))) {
				if (sha1.substring(8).equals(rs.getString(2))) {
					loginUserChkFlag = true;
				}
			}
		}
		return loginUserChkFlag;
	}

	/**
	 * @param userId - 繝ｦ繝ｼ繧ｶ繝ｼID縲�
	 * @param password - 繝代せ繝ｯ繝ｼ繝峨��
	 * @return 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｫ邂｡逅�閠�繧呈諺蜈･蜃ｺ譚･縺溘ｉtrue縲∝�ｺ譚･縺ｪ縺九▲縺溘ｉfalse縲�
	 * @throws SQLException縲ゅョ繝ｼ繧ｿ繝吶�ｼ繧ｹ蜃ｦ逅�縺ｫ蝠城｡後′縺ゅ▲縺溷�ｴ蜷医��
	 * @throws NoSuchAlgorithmException縲ゅ≠繧区囓蜿ｷ繧｢繝ｫ繧ｴ繝ｪ繧ｺ繝�縺瑚ｦ∵ｱゅ＆繧後◆縺ｫ繧ゅ°縺九ｏ繧峨★縲∫樟蝨ｨ縺ｮ迺ｰ蠅�縺ｧ縺ｯ菴ｿ逕ｨ蜿ｯ閭ｽ縺ｧ縺ｪ縺�蝣ｴ蜷医��
	 * 邂｡逅�閠�繝ｦ繝ｼ繧ｶ繝ｼ縺ｮ諠�蝣ｱ繧呈眠隕剰ｿｽ蜉�縺吶ｋ縲�
	 */
	public boolean insertUser(String userId, String password) throws SQLException, NoSuchAlgorithmException {

		// 繧ｪ繝ｼ繝医さ繝溘ャ繝医ｒ辟｡蜉ｹ縺ｫ縺吶ｋ
		con.setAutoCommit(false);

		boolean insertUserChkFlag = false;

		// user_id縺後�槭ャ繝√＠縺溘Θ繝ｼ繧ｶ繝ｬ繧ｳ繝ｼ繝峨ｒ蜿門ｾ励☆繧�
		String sql = "select * from m_user where user_id='"
				+ userId + "';";
		ResultSet rs = st.executeQuery(sql);

		//繝代せ繝ｯ繝ｼ繝峨ｒ繝上ャ繧ｷ繝･蛹�
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] passwordDigest = digest.digest(password.getBytes());
		String sha1 = String.format("%040x", new BigInteger(1, passwordDigest));

		//繝�繝ｼ繝悶Ν縺ｮuser_id繧偵メ繧ｧ繝�繧ｯ縺励※蜷後§蛟､縺後↑縺九▲縺溘ｉ縲「ser繝�繝ｼ繝悶Ν縺ｫinsert縺吶ｋ
		//螟ｧ譁�蟄怜ｰ乗枚蟄励メ繧ｧ繝�繧ｯ
		if (!rs.next() || !userId.equals(rs.getString(1))) {
			sql = "insert into m_user values('" + userId + "','" + sha1.substring(8) + "', null);";
			int result = st.executeUpdate(sql);

			// 豁｣縺励￥霑ｽ蜉�縺ｧ縺阪◆蝣ｴ蜷医�√さ繝溘ャ繝医☆繧�
			if (result > 0) {
				insertUserChkFlag = true;
				con.commit();
			}

		}

		return insertUserChkFlag;
	}
}
