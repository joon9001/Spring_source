package pack;

public class LogicImpl implements LogicInter{
	private ArticleInter articleInter;

		public LogicImpl() {
			// TODO Auto-generated constructor stub
		}
		
		public LogicImpl(ArticleInter articleInter) {
			this.articleInter = articleInter;
		}
		
		@Override
		public void selectDataProcess1() {
			System.out.println("selectDataProcess1 수행");
			articleInter.selectAll(); // 모델 클래스 수행 결과
			
			System.out.println("----------------");
		}
		
		@Override
		public void selectDataProcess2() {
			System.out.println("selectDataProcess2 처리");
			articleInter.selectAll(); // 모델 클래스 수행 결과
			
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
