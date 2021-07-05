package ru.leo.simbirsofttask;

import ru.leo.simbirsofttask.dsprovider.DataSourceProvider;
import ru.leo.simbirsofttask.model.WordFrequency;
import ru.leo.simbirsofttask.repository.WordFrequencyRepository;
import ru.leo.simbirsofttask.service.WordFrequencyService;
import ru.leo.simbirsofttask.util.ContentExtraction;
import ru.leo.simbirsofttask.util.HtmlConverter;
import com.google.common.net.InternetDomainName;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        String content = ContentExtraction.getHtmlContent(url);
        List<WordFrequency> wordFrequencies = HtmlConverter.toWordFrequency(content);

        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        WordFrequencyRepository repository =
                new WordFrequencyRepository(
                        dataSourceProvider.getDataSource(),
                        "word_stat_of_"
                                + url.replaceAll(
                                        "http://|https://|www\\.|ws://|wss://|/|\\.*",
                                        ""));

        WordFrequencyService service = new WordFrequencyService(repository);

        if (service.getAll() == null) {
            wordFrequencies.forEach(service::insert);
        }
        service.getAll().forEach(System.out::println);
    }
}
