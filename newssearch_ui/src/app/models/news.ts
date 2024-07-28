export class NewsArticle {
  date: string;
  articles: Article[];
  constructor(date: string, articles: Article[]) {
    this.date = date;
    this.articles = articles;
  }
}

// export class ArticleGroups {
  
// }

export class Article {
  artId: string;
  source: Source;
  author: string;
  title: string;
  description: string;
  url: string;
  urlToImage: string;
  publishedAt: Date;
  content: string;
  constructor(
    artId: string,
    source: Source,
    author: string,
    title: string,
    description: string,
    url: string,
    urlToImage: string,
    publishedAt: Date,
    content: string
  ) {
    this.artId = artId;
    this.source = source;
    this.author = author;
    this.title = title;
    this.description = description;
    this.url = url;
    this.urlToImage = urlToImage;
    this.publishedAt = publishedAt;
    this.content = content;
  }
}

export class Source {
  id: string;
  name: string;
  constructor(id: string, name: string) {
    this.id = id;
    this.name = name;
  }
}
