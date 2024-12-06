package webscraping.models;

public record ScrappingResult(String threadName, String url, long duration, int dataSize, String status, String title,
                              String description, String keywords, String bodyText) {

    @Override
    public String toString() {
        return STR."ScrappingResult{threadName='\{threadName}', url='\{url}', duration=\{duration}, dataSize=\{dataSize}, status='\{status}', title='\{title}', description='\{description}', keywords='\{keywords}', bodyText='\{bodyText}'}";
    }
}
