package lvchanglong

/**
 * 术语
 * @author lvchanglong
 *
 */
class Term {

	static belongsTo = [lan: Lan, discipline: DisciplineP, yongHu: YongHu, entry: Entry] //语种，学科，用户，条目
	
	String name //名称->导入
	String area = '无关'//地域
	String dy = '无定义'//定义->导入
	String ly //来源

	//导入：名称(name)、定义(dy)、语种(lan)、学科(discipline)、用户(yongHu)、条目(entry)、来源(ly)
	//特有：地域(ly)
	static constraints = {
		name(nullable: false, blank: false, unique: true)
		area(nullable: true, blank: true)
		dy(nullable: true, blank: true)
		ly(nullable: true, blank: true)
		
		lan(nullable: false, blank: false)
		discipline(nullable: true, blank: true)
		yongHu(nullable: false, blank: false)
		entry(nullable: true, blank: true)
	}
	
	static mapping = {
		table 'TERM'

		name column: 'NAME', sqlType: 'varchar(130)'
		area column: 'AREA', sqlType: 'varchar(30)'
		dy column: 'DY', sqlType: 'varchar(255)'
		ly column: 'LY', sqlType: 'varchar(255)'

		lan column: 'LAN_ID'
		discipline column: 'DISCIPLINE_ID'
		yongHu column: 'YONG_HU_ID'
		entry column: 'ENTRY_ID'

		id column:'ID'
		version false
	}
	
	String toString() {
		return this.name
	}
	
	/**
	 * 术语检索(注：使用LIKE操作时，如果条件以通配符开始（ '%abc...'），MySQL无法使用索引)
	 * @param q 询问
	 * @return
	 */
	static def search(String q) {
		def dc = Term.where {
			name ==~ q.trim() + "%"
		}
		return dc.list([max: 10])
	}
	
}