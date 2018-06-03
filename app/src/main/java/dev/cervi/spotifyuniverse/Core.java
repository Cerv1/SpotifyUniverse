package dev.cervi.spotifyuniverse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.UUID;


public class Core extends Activity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback
{
    private static final String CLIENT_ID = "cb14ff08ff514584b4b7e2ead3b94376";

    private static final String REDIRECT_URI = "spotifyuniverse://calback";

    private boolean paused;

    private static final String[] TRACKS = new String[] {
        "3AhXZa8sUQht0UEdBJgpGc",
        "2PzU4IB8Dr6mxV3lHuaG34",
        "7pKfPomDEeI4TPT6EOYjn9",
        "34b3a3Pz9Jlz0092LMyNAB",
        "7s25THrKz86DM225dOYwnr",
        "2oF7FZHIJbzjeEXZ3D0Ku4",
        "3HMrMZ56giBGJYcCMSRijs",
        "0aym2LBJBk9DAYuHHutrIl",
        "5ghIJDpPoe3CfHMGu71E6T",
        "5yQ9iMZXGcr5rlO4hoLsP4",
        "45s88Xopo6KvHc0PQ05aGg",
        "0YfOnJWqmAKaUvEL1QcNop",
        "3BQHpFgAp4l80e1XslIjNI",
        "18GiV1BaXzPVYpp9rmOg0E",
        "5jzma6gCzYtKB1DbEwFZKH",
        "4pbG9SUmWIvsROVLF0zF9s",
        "0wJoRiX5K5BxlqZTolB2LD",
        "22MbC8gTKpaqNTrmgXQfbJ",
        "64Ny7djQ6rNJspquof2KoX",
        "7iN1s7xHE4ifF5povM6A48",
        "6hTcuIQa0sxrrByu9wTD7s",
        "2G2YzndIA6jeWFPBXhUjh5",
        "3KfbEIOC7YIv90FIfNSZpo",
        "0izzSOqBR4gdzCCWRnrzPd",
        "6iGU74CwXuT4XVepjc9Emf",
        "0hKRSZhUGEhKU6aNSPBACZ",
        "2kkvB3RNRzwjFdGhaUA0tz",
        "4Y4Gd3ty8uut6Qw43c7yJc",
        "7hxZF4jETnE5Q75rKQnMjE",
        "5CQ30WqJwcep0pYcV4AMNc",
        "1iDcKYNvo6gglrOG6lvnHL",
        "19jo0UT2vqD4pNVfIqTy4R",
        "5GMw6X6BbCDGO9gApQYRSa",
        "5uvosCdMlFdTXhoazkTI5R",
        "3HBvs6g8WgT8KT1gZzGSX1",
        "423hwXFgoN8RYmqLoLuVvY",
        "6H3kDe7CGoWYBabAeVWGiD",
        "1PVFVBYxZVsbJnnxAlEmbU",
        "5wJcMwXuy1x6EatowWOxha",
        "0sDqo9UPzPUtu9wEkI3zRB",
        "199y2CrnN7lWEp862Nsyfb",
        "2vXk7PcNLLXsdnVaoMxzTj",
        "6PbclyMA0GpMTym4eDYQ1I",
        "6xNwKNYZcvgV3XTIwsgNio",
        "5j6ZZwA9BnxZi5Bk0Ng4jB",
        "6l8EbYRtQMgKOyc1gcDHF9",
        "2aoo2jlRnM3A0NyLQqMN2f",
        "40riOy7x9W7GXjyGp4pjAv",
        "7FwkoTz8Ic8C5YaE9XXGIU",
        "5DuTNKFEjJIySAyJH1yNDU",
        "77GLApi9hqJKMYEe8QybtA",
        "3LoDeIbiR12sAznmpNEmKA",
        "51FpzuGkRYXFgsE2zXt9av",
        "0iA3xXSkSCiJywKyo1UKjQ",
        "6n3HCYuumzxe00S1fBNbUk",
        "3qitymULqEibr7yknRMKU4",
        "5ChkMS8OtdzJeqyybCc9R5",
        "52vA3CYKZqZVdQnzRrdZt6",
        "7kWhdmRYv8CqbWNqfojqVd",
        "2L1YTEDraxbH7AQdJvblxv",
        "6s9Srt9YlqeWgWtqzO0UkL",
        "1qRA5BS78u3gME0loMl9AA",
        "6nEkxYIEnrbYH7h1hJ8Xn6",
        "6FRwDxXsvSasw0y2eDArsz",
        "26PwuMotZqcczKLHi4Htz3",
        "4gphxUgq0JSFv2BCLhNDiE",
        "6Vcwr9tb3ZLO63F8DL8cqu",
        "6eLL7QTdMWdhhG4i3jHDR9",
        "6y6KOwYsmPXhiOTayBpoBz",
        "6bJuuCtXYiwOcKT9s8uRh8",
        "6v4gV9s0pnwKbmF1yk5knO",
        "44XGMLszaOWD7k4LsJMcWa",
        "4dwrL3Z5U2RZ6MZiKE2PgL",
        "0hCB0YR03f6AmQaHbwWDe8",
        "3Am0IbOxmvlSXro7N5iSfZ",
        "4LCoCwnv3R54Gbnj7aLr3f",
        "0rTkE0FmT4zT2xL6GXwosU",
        "2HCaIYjkvWSZzaSKUoOh3d",
        "4tMxTsEPybFSX1dq7BOC4K",
        "2NkAoxQOr6MGdMB5JDTU81",
        "00xR9dHhuaNznqB4FSzOlr",
        "1H7gMYGykdtwZOV6s1N0by",
        "2sXp9Qmvc7mRaDBjBgcGGi",
        "4DuBNU1r50XEv4dvsrQcpY",
        "56lhDZNQ5J47aog6mGKeGk",
        "6YffUZJ2R06kyxyK6onezL",
        "6jWkZvd1URGktyTTwcpPpB",
        "4s6LhHAV5SEsOV0lC2tjvJ",
        "5QEJbqvNNkFWzyF1l8d2Ci",
        "1OtWwtGFPXVhdAVKZHwrNF",
        "33iQW2OneB0oNh2NfrAzqW",
        "72AO8u6lcjKp0CBeaPZT8I",
        "1s1kY9duH0g4jKHrxxjTkX",
        "7bglJCaprPQTfDfovdJS2h",
        "4waQv4bln6SaCB51Qj9Fp6",
        "7b2rLb86ZhpYcu55JOxkJK",
        "13FkFPnKX1eC2V3nwSy5YB",
        "4BP3uh0hFLFRb5cjsgLqDh",
        "6lFZbCc7pn6Lme1NP7qQqQ",
        "2AxCeJ6PSsBYiTckM0HLY7",
        "63tznJW5FCf5jM7hgYvEMe",
        "424pNN5XqYHje4vNpuHgBT",
        "583YTL8Fl6pCWtZAi2GvVZ",
        "76TZCvJ8GitQ2FA1q5dKu0",
        "3RkQ3UwOyPqpIiIvGVewuU",
        "7nBYE4hrFEJKFfCnk5y94X",
        "4reRa9oggVNyZjkceS7LaH",
        "3yrSvpt2l1xhsV9Em88Pul",
        "36Ec6qkBtFF2XYj51zs8L6",
        "3l49MeO0nNZhBj1IfYWB5w",
        "4O958xeV2blAhD95Qo24nc",
        "7pOWWGpUc3T05IXfymgIP3",
        "0fonScmHUzC0MglSLn9saA",
        "5aivQ5CkXXHJoMLy0o92HL",
        "3RZMzCvYsmJ0u2ioKTOsmJ",
        "7a7drPRRR0mY7kOJDvDWjq",
        "0wZlVQWKaOxC51arp7X3uM",
        "07GvNcU1WdyZJq3XxP0kZa",
        "0PGwM5vdr5fMejx0IIAYXj",
        "3SdTKo2uVsxFblQjpScoHy",
        "61Q9oJNd9hJQFhSDh6Qlap",
        "6AIB4vBOGWM4FJ65yNWaXu",
        "1gqTXnc7WXBI9AwcCiqcJ1",
        "7vjrRo9zu1pvrqLe6A5Yex",
        "7aLoa1F3EoM8AeC6Ao1RKz",
        "0LrwgdLsFaWh9VXIjBRe8t",
        "4zI74TsTMqQcrTpMuWk7f5",
        "1nzcKrYRTWZJEwWzyAU8XM",
        "6c20LH3R4foocWLmYyojWq",
        "5JGEAz15LkPoOtFHttDtVs",
        "7kGh7fOpjmadlaxLwQ9pdA",
        "1W1GpfPujmgp2vQqcpUhtU",
        "4NRQwaks9r58tTDvr4iEyv",
        "389QX9Q1eUOEZ19vtzzI9O",
        "38zsOOcu31XbbYj9BIPUF1",
        "5GjPQ0eI7AgmOnADn1EO6Q",
        "2GVEs1sn5Q1jB1976vQoAX",
        "3KiexfmhxHvG5IgAElmTkd",
        "2nVHqZbOGkKWzlcy1aMbE7",
        "7dblNGnRXEBrVJunazs2U5",
        "4YIrFeFhNQcPppVqq5JczW",
        "5dYJT6qthbXYz2A1tanERP",
        "6vvmYYUvGXtZLU8msxKvzF",
        "7lL2lMWNtzOcf5HnEudNgn",
        "2Q5wSOwq6BDSu7sSVMNrtT",
        "3yP0cohcr97BUNJcgvmSVg",
        "1IqFh00G2kvvMm8pRMpehA",
        "1MA9StLzlFftLbuqOmoWij",
        "5v3utF8InHZKd8JZJK5rCx",
        "75Mwp7YsQRk0ZBq1lOdL2T",
        "23MrkN7g6Q5U7GLIxNHN1B",
        "5J2CHimS7dWYMImCHkEFaJ",
        "2HxORz9IQMuXjZTXNnoxpc",
        "6mv76kovZyVB0UHbqUsee0",
        "39CGrUZUozjtjgAASzzB56",
        "6UFJAngOupWGd28mX6IapK",
        "2XBsQSZqHPPAtZpRG6TvIm",
        "5Q4OndPgegGCR88wPsXswf",
        "6jg8Y7gArYgZeXUBPMre0V",
        "2FTCUHcXNicisIq19seZ2b",
        "3v26Zwq3GP26YOJx3QCpBg",
        "7tFiyTwD0nx5a1eklYtX2J",
        "3ftnDaaL02tMeOZBunIwls",
        "2M9ro2krNb7nr7HSprkEgo",
        "5Z01UMMf7V1o0MzF86s6WJ",
        "0jHkgTtTaqg5LNCiYDQPUB",
        "1zC9mSQAH2fn3DGscYRuCY",
        "6FBlNlVx0xrXPb9suicaMM",
        "1pjATX7sbd6Y4jMVqIvzHk",
        "4NtUY5IGzHCaqfZemmAu56",
        "5MxNLUsfh7uzROypsoO5qe",
        "6ui6l3ZNvlrGQZArwo8195",
        "7lY8aoN3wUR3NY4nUwigPv",
        "6Um3XCAxl9YfFmAnGl93xc",
        "1GLmaPfulP0BrfijohQpN5",
        "5tVA6TkbaAH9QMITTQRrNv",
        "74njazGdnO2igjEkb0mGiy",
        "1r8oPEXqnhUVgkUkJNqEuF",
        "3AszgPDZd9q0DpDFt4HFBy",
        "4fQMGlCawbTkH9yPPZ49kP",
        "391TUcoPonqYykPkSZ5Z9U",
        "6gRGLfswEsfpy1UxHEyP6X",
        "5ZBeML7Lf3FMEVviTyvi8l",
        "4n1ZGm3TxYmoYe1YR8cMus",
        "08mG3Y1vljYA6bvDt4Wqkj",
        "3Bh6uInhcVBVvLraGZdkKD",
        "5cP52DlDN9yryuZVQDg3iq",
        "0MzmrBuRrGPPo6rZIa4rsu",
        "5EWPGh7jbTNO2wakv8LjUI",
        "6V9VCm1zOY2lGR80RehJ9i",
        "3cfN34fxSbtaJ5HmSDrdAX",
        "5SWqbaVQI5EgILjYVniKqe",
        "2xMAFTxwZ0w1twuyMZcYb7",
        "4MhTFsyqIJnjsOweVcU8ug",
        "01u6AEzGbGbQyYVdxajxqk",
        "0NWPxcsf5vdjdiFUI8NgkP",
        "6Ie9yuocD61v7hrh02moc6",
        "5PntSbMHC1ud6Vvl8x56qd",
        "6wVViUl2xSRoDK2T7dMZbR",
        "2EqlS6tkEnglzr7tkKAAYD",
        "4heMx0OAwfILu13Lf0VbBM",
        "50W9PJjr1Euo9FFoqw90eL",
        "20kN7bu0HMO8rIVY6tEytW",
        "2Y8wN1dpvzHdjV5cwza5wV",
        "6oRHRkQigTzJ3KwpO3XOV6",
        "1DKyFVzIh1oa1fFnEmTkIl",
        "62fX8EW16l8St2yL8rMer9",
        "4PEeZ2U4UfP2Jo8EtIOjus",
        "5RSQKtG1KNwrzFMEePpjt6",
        "5yMp0quAWTb2lSEvWbAtqj",
        "7gyVDHcFWxHCh2iqj2enR6",
        "4Y7fEQ4PAzhlLnLviRw2P4",
        "5TiUTAPurormiQX9gE0CAQ",
        "1udKn1oNKYQSQ9OmiIWCMu",
        "2SpEHTbUuebeLkgs9QB7Ue",
        "2Mr1bGI2E10K7Mt1UJZ6Mw",
        "4htRmfD1DlMeuuxURDd3ub",
        "4wfK7KMFTFMNDDfKDe1aWi",
        "5p3JunprHCxClJjOmcLV8G",
        "52HAHV1j93s5B8GoTNI7DJ",
        "1MQWtVcs0PKsY4PA6ZvLiy",
        "0G3fbPbE1vGeABDEZF0jeG",
        "3KSchPNSklO5McIqRH3qYX",
        "3lh3iiiJeiBXHSZw6u0kh6",
        "3LcYYV9ozePfgYYmXv0P3r",
        "39shmbIHICJ2Wxnk1fPSdz",
        "58PSYdY0GFg0LFb2PxYk4T",
        "37Dl7jQMmt0gUnzTKqnjkN",
        "7s1upm7yr7ZjrzXMwHawtG",
        "0PkBTqRtN25z3oKasWoKlj",
        "25L7DvNU798tfl5SgKgnt1",
        "2hIGz0CXlqA2ukeuUl8DYP",
        "2xar08Fq5xra2KKZs5Bw9j",
        "64vTNPY5ATO32nkar9WK7G",
        "16SMGN0veGPrKOS2U2tQkr",
        "1xyBQeZBGAlUFkY8blLpUz",
        "4EoUALVwY4i7U6uKRhk8hr",
        "7odHgoLFi3GQ90E9PeraI3",
        "4ZVZBc5xvMyV3WzWktn8i7",
        "3gdewACMIVMEWVbyb8O9sY",
        "4W4wYHtsrgDiivRASVOINL",
        "3yobZXbEQQJq7wBazWGDVg",
        "2x0supcZhd6NEH1nem9DnR",
        "4TBBPZks71c60whhq0PgdP",
        "4t6NQkpUmvpvLDCkl3XuwA",
        "0FWhGmPVxLI6jOVF0wjALa",
        "2oZmMp5M6L0Rh7G84Um2tF",
        "1Y373MqadDRtclJNdnUXVc",
        "0YTgZJfVLmJ0b8XAMZKRKg",
        "2xSoBcjJX9MlC2WyW5hf3t",
        "0WNGPpmWqzPnk0psUhJ3SX",
        "2zYzyRzz6pRmhPzyfMEC8s",
        "0a4agFmqHXxcZl1nho1BxM",
        "2nTsKOXIVGDf2iPeVQO2Gm",
        "0D9J4MkQVyn1690CBqLx4u",
        "2nlSeJ6CgvWeVOkrLmadf6",
        "74X1epeRufHckhuX1KFD04",
        "2ht21Rlf8L6cPLBkYmhpcs",
        "0dMd4rilfd6gPbXaLpNYhu",
        "2cc3PDGjT6BRBHwa58mzrD",
        "0mG8Hety4RELeo6p808fcy",
        "0ySB245mUacvFImmooEzuk",
        "0qKCm8yeHoRJTpryDDQ9TT",
        "6LHzCuLzfZQr0V8eZD9TPC",
        "4AwKXevZmsTNa3KZVj3rzl",
        "6C4LXC9UFH1IKiHYOp0BiJ",
        "5E5HYgxGMp3BPakHGfKfIB",
        "4BcrWCZPXuoGU0SnAsJW1s",
        "2ULL3VZf4WwBKO4vjwT7Bg",
        "4DEbXdYvto9BB3TyYyxLKn",
        "0pNeVovbiZHkulpGeOx1Gj",
        "4uGIJG1jYFonGc4LGp5uQL",
        "0dOg1ySSI7NkpAe89Zo0b9",
        "5YLnfy7R2kueN0BRPkjiEG",
        "0pQskrTITgmCMyr85tb9qq",
        "5CgHsJyqFuW1U0E3d4Eyi6",
        "0SMRck5oZi9hc16L6FuRF6",
        "1k1Bqnv2R0uJXQN4u6LKYt",
        "0n2pjCIMKwHSXoYfEbYMfX",
        "0tVzXGFyNPusa1VkHmYDLd",
        "0vLwL4xuJ3s7SeaCdvMqkY",
        "0dhzPXfQUdjNVsjVV7n4iD",
        "3Cw5corFYZsKc6dIbOBXgl",
        "6qUEOWqOzu1rLPUPQ1ECpx",
        "0QMFMQF3HoSk5WvMyx8aMT",
        "38Vb1J5W5LOs0i7SAF76pa",
        "3dzW0SoVNyJTWWJimX8stj",
        "6ITuEsxEy2qPhqMowdDAeI",
        "6d9rNfRFOQ0XmsUwJ8C5J0",
        "3qT4bUD1MaWpGrTwcvguhb",
        "09t1lDivsjZffcTC7rNa2m",
        "1Bh3GMDtYN2FB4jWp7VlrZ",
        "1Q1b8eVkUPGlpSArl8JAVw",
        "5Th6EdNVhxp0TPX41ZLZk6",
        "0bYFgxCKQ5P4yrYJ65ytB9",
        "1z3ugFmUKoCzGsI6jdY4Ci",
        "3wsPg2KrRYZFi0inIFa41x",
        "7ETtI1QTDcdI8yL55eXVOF",
        "4hupcimlg3UBbW1kAQ6vrT",
        "2RnPATK99oGOZygnD2GTO6",
        "5opl7znewPv98Jrm8Y49Xw",
        "7MH7TkjQ4RA1vG454dciw0",
        "0QlHvmgHQ66FFaSbKkVYJM",
        "4xPNaHofsEWeDnD63eIUXp",
        "4kOfxxnW1ukZdsNbCKY9br",
        "3IOQZRcEkplCXg6LofKqE9",
        "11fNLqDB47gMKj7BHhR2Qr",
        "5YsyqcewwE0c1ukzHVciS3",
        "1YwoYPfhuZlLDbJUH1cKSi",
        "5HNCy40Ni5BZJFw1TKzRsC",
        "7vYA9ET5AUqJt5pBbhKmcB",
        "6mFkJmJqdDVQ1REhVfGgd1",
        "4675plADodrFbExkQuE2jt",
        "1v98rfd0an913AzHvMNG8a",
        "5Z8EDau8uNcP1E8JvmfkZe",
        "6WE7jSshLCuVKoCmobVKVf",
        "2RxqiKD6ztd7nT9w1uwiNC",
        "1yo16b3u0lptm6Cs7lx4AD",
        "4kPSjEg8u1U4pg2dHHMmtf",
        "5uES1C2NgkdrNHiCwf9jRr",
        "0XnhQGfqfi5CnZXcONJuI5",
        "7tMCIOFe99cWEYr21wfsDp",
        "5DtrODn94SgpAZOwxHLfBt",
        "5UgT7w6zVZjP3oyawMzbiK",
        "0RgcOUQg4qYAEt9RIdf3oB",
        "54flyrjcdnQdco7300avMJ",
        "69uJi5QsBtqlYkGURTBli8",
        "6k9DUKMJpWvu6eFG3O64Lg",
        "0Y2SrByf4G3kbq2nBEHQRn",
        "52dm9op3rbfAkc1LGXgipW",
        "3cw3Z7mS55gd3NUjh4k0bL",
        "2vX5WL7s6UdeQyweZEx7PP",
        "1OOtq8tRnDM8kG2gqUPjAj",
        "34jOBavlBgAG0ILZOlXmWm",
        "6ESGTTiBkHRiYPiIufJk97",
        "3qiyyUfYe7CRYLucrPmulD",
        "63yKj3bpZUCWcJ4Xh6Ygl1",
        "1DndHckdH9m5rp6gYP086b",
        "7004I49tDafH7LqMNQaD7N",
        "1xKQbqQtQWrtQS47fUJBtl",
        "2aEeghgUcnu75tzcolFMfs",
        "5JCKZcLwTEHKSFiDSojxw7",
        "0zrGFRlWIdOohOxxgFdPU6",
        "4MexO1G9TadTu0yWDB3T7Q",
        "3UCmuRdeTriWgOZMEJsfqZ",
        "53UXqZaarqFPAFEy7dT1m1",
        "6Q2pkSKuM08SDOuAS5amQD",
        "5ueyLj6e6oVaTY0KQ6yLaA",
        "612VcBshQcy4mpB2utGc3H",
        "0KrYPz4S4mKHeBjCwYJp0s",
        "66FSV5dLK5sNLZ00IfHxfD",
        "1TfqLAPs4K3s2rJMoCokcS",
        "1Eolhana7nKHYpcYpdVcT5",
        "51HaFqOPAJFfFNycct9IWR",
        "7GSa90z6YJnvpIJTL0Mfnk",
        "3gsCAGsWr6pUm1Vy7CPPob",
        "0mlwPYU0ApElSjZN4yA4L9",
        "68BTFws92cRztMS1oQ7Ewj",
        "6RJK553YhstRzyKA4mug09",
        "1jFhnVoJkcB4lf9tT0rSZS",
        "1gIKd24bixkgYzuxse32oR",
        "3Xls4cNOwy01dtrNXb1inG",
        "1xShPgQbOUa98avWJQFDBY",
        "5kXJVyFqJdAv6XzjFeKqv7",
        "5H6Jp0syB5yEPk7SWYdlmk",
        "1GA4kYQHVXb6uyvgftyHvv",
        "7Me0vOSlJfaPY7Pc4GeItd",
        "2g2GkH3vZHk4lWzBjgQ6nY",
        "67by2uTSabJjy4FkJKt5Nj",
        "4UjU8YmDOrw1yIFqYd1Afo",
        "045sp2JToyTaaKyXkGejPy",
        "3Q7LXyGZ3SLD0dLztx3j5x",
        "3dh2LlmeMqKJbzn2WUgt3d",
        "46dGFTD918NMz1IP1rPJXO",
        "3iJxloILmOu2NIRZ0OGyui",
        "7GSRH3L0HAM3StkAkYVamC",
        "5yEPxDjbbzUzyauGtnmVEC",
        "5JBUpI6OGZahUqchMKe6UY",
        "7nhrlsT1P4qsslp28UA1BI",
        "0ToHhkK4qtwEyKOxhQpMbJ",
        "5D2lp16FQ0VIfLGRDn5jcG",
        "2TVxnKdb3tqe1nhQWwwZCO",
        "0SYRVn2YF7HBscQEmlkpTI",
        "3gbwcu7E9ACpJsLm7vEpuN",
        "6ClsM1x4P327baDUXp2Dep",
        "24GBNWw5fZBbxC9QLB3nDk",
        "74JdR9aXE6I74oS1BVRsvb",
        "7AzFID6u1b3zIWbd9pb8Dk",
        "44AyOl4qVkzS48vBsbNXaC",
        "0dEWNe59HErkcoQNqncgCb",
        "3BQLTtpQOYGXQY2lT3ZZeU",
        "5QTxFnGygVM4jFQiBovmRo",
        "7e89621JPkKaeDSTQ3avtg",
        "1hKdDCpiI9mqz1jVHRKG0E",
        "3T5gr9dfVj22gqQvfcSPYl",
        "7eKl9sSHQDPKJueLy3Ofuu",
        "74iQ3gahRTOGc19bYadBE3",
        "7Lfp7FsKI4buEJYbMlt7QU",
        "2rslQV48gNv3r9pPrQFPW1",
        "4MQRDF62mf2KW7I8M1S9qA",
        "2RzJwBCXsS1VnjDm2jKKAa",
        "30HCB1FoE77IfGRyNv4eFq",
        "24NwBd5vZ2CK8VOQVnqdxr",
        "1sxtxIKhRiQwuGpUwHoEHq",
        "1lFC3sMgOcDrVzNh8zXRnl",
        "4rS63BySQrdWuTswkkZ5iS",
        "4FdDorlbJTVHcH3djLbIfn",
        "3SjU8wBPRbYHO5UzvMztwi",
        "55f4JiHsK46ftCjzr5CiKl",
        "3aSWQJcWnnqgwYbAgidvlV",
        "4gvea7UlDkAvsJBPZAd4oB",
        "5n8Aro6j1bEGIy7Tpo7FV7",
        "4kUV72Pe5TK6sVMDJI6CqA",
        "5TUa3AsgSh3kJIuXn42wig",
        "3FCto7hnn1shUyZL42YgfO",
        "0c2ta2eSTOMDpGHsitgb5c",
        "47gmoUrZV3w20JAnQOZMcO",
        "4hq0S6wznq7SHDyMOFXL9i",
        "6SwqPZs0XuSnyqkbnkkAjQ",
        "5SAUIWdZ04OxYfJFDchC7S",
        "0nNSe20usqaijlOpIhmwzt",
        "2HV8lNIQ45pCDfQBQ1IIXA",
        "4NL8D4RQsC6ux6eI3m3Lg5",
        "45xBWOa6oEAk1WlVyr5QAB",
        "4TtymnqQiN24mkJ8M2AjI5",
        "6UtfMFnVWLJjHsd3dPcGgB",
        "3MODES4TNtygekLl146Dxd",
        "5Sz09kaSzvpTC8lgm5W8Mt",
        "77oU2rjC5XbjQfNe3bD6so",
        "1XuccRABkfUVB4FjSVhjL1",
        "0aDmZSsj0OqOcscNlsASlm",
        "672N8DGGTOLCOgWe0koX5g",
        "5JKQMoGmXwDHCodn5pFVHB",
        "0TT7wJiEYD5GAeJfSR1ETX",
        "0yPbJmOSpFO4YoVeFMdURv",
        "1QRsFCbeZi4dOtBCadncZ6",
        "2SWBfqj1FrS8t8z56G55rP",
        "4P5KoWXOxwuobLmHXLMobV",
        "6wzLLGFlWQ5jqTL13MU069",
        "7xsvZuhTQEGsLfopzFjMsS",
        "1h04XMpzGzmAudoI6VHBgA",
        "3ohLnESFgYACPMCkoTOzqE",
        "6VuO6xcpSeeLfqBo4ePbkw",
        "5Q41NLTmGbVPozwHKK7bk2",
        "0qdQUeKVyevrbKhAo0ibxS",
        "3bH8PZsfbCRIoeNiFm7JmU",
        "4MUGG9mgDUP8dlaS3AAeg0",
        "2UnY8ApZT4roi66n1LDAil",
        "6eJlEcRmeyQfTlDQBDyqkW",
        "3gZCHwYEwNnHxF5P0Sh6NB",
        "6vcG9EgeqJUSmmF65lxUm6",
        "3JuDFJGjRpK4k8ggzfMPSO",
        "4lzTyLYXb3ED01TSLCJ1Hz",
        "48TNLcToLs8DWkdj5vYdiW",
        "6VRkC5p1m3D412ilP0UFPq",
        "2ccUQnjjNWT0rsNnsBpsCA",
        "6jGMai6vxGIF1e0YLMmasR",
        "5NIPsWpDjJTFBoPxCUUeXp",
        "6tIHdxL5JKTIsicGA660tm",
        "12q3V8ShACq2PSWINMc2rC",
        "2by5mqpQ1ZP2G5FOIccMnu",
        "51wQovDO0hf05pkZYvu1GI",
        "6uAEJQpAPoYmM6Mb0tJN2I",
        "3vnEU0MPBhUD5ihhQiV42f",
        "0Puj4YlTm6xNzDDADXHMI9",
        "1JLn8RhQzHz3qDqsChcmBl",
        "2dCmGcEOQrMQhMMS8Vj7Ca",
        "4vpeKl0vMGdAXpZiQB2Dtd",
        "3GGcwG519BTMdvMeFy7meT",
        "2mAguyBQ2Xey2yOmwIpL3B",
        "3YU9X8ryOR20beT7wOlDIJ",
        "76GlO5H5RT6g7y0gev86Nk",
        "1TOP1J3oGqQHpkJZuUnCjN",
        "0qxYx4F3vm1AOnfux6dDxP",
        "1YrnDTqvcnUKxAIeXyaEmU",
        "65jrjEhWfAvysKfnojk1i0",
        "054cn0O2XzKkUidjBFQocr",
        "7cv28LXcjAC3GsXbUvXKbX",
        "61UuPxxYUvacEH6SHIK3sU",
        "0q8nQ0H4ad4KYcZdrTZPux",
        "4MZEZz8MqVgvIMXU6AVP22",
        "5usNPz6U1acDrkUYekGYDY",
        "2TjnCxxQRYn56Ye8gkUKiW",
        "77Z1pij1PpjhlzZnzBM86G",
        "3hJLKtTpgct9Y9wKww0BiR",
        "7GptbanebPZYkLPvjNfd6m",
        "2zbV4xRYLuElz4PWOXI5P7",
        "43DeSV93pJPT4lCZaWZ6b1",
        "1QEEqeFIZktqIpPI4jSVSF"};


    String mAccessToken;
    private Player mPlayer;

    // Request code that will be used to verify if the result comes from correct activity
    // Can be any integer
    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        paused = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        // The only thing that's different is we added the 5 lines below.
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        // The next 19 lines of the code are what you need to copy & paste! :)
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(Core.this);
                        mPlayer.addNotificationCallback(Core.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        //Log.w("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.w("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.w("MainActivity", "User logged in");
        // This is the line that plays a song.

    }


    public void playRandomSong(View view){

        Random r = new Random();
        mPlayer.playUri( "spotify:track:"+TRACKS[r.nextInt(TRACKS.length)], 0, 0);
        TextView t;
        mPlayer.pause();
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.w("MainActivity", mPlayer.getMetadata().toString());
        if(mPlayer.getMetadata().currentTrack != null){
            mPlayer.resume();
            t = findViewById(R.id.text_artist);
            t.setText(mPlayer.getMetadata().currentTrack.artistName);
            t = findViewById(R.id.text_song);
            t.setText(mPlayer.getMetadata().currentTrack.name);
            t = findViewById(R.id.text_album);
            t.setText(mPlayer.getMetadata().currentTrack.albumName);
        }
    }

    public void pauseResumeSong(View view){
        Button b = findViewById(R.id.button_pause);
        if(paused){
            paused=false;
            mPlayer.resume();
            b.setText("Pause");
        }
        else{
            paused=true;
            mPlayer.pause();
            b.setText("Resume");
        }
    }


    @Override
    public void onLoggedOut() {
        Log.w("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(int i) {
        Log.w("MainActivity", "Login failed");

    }

    @Override
    public void onTemporaryError() {
        Log.w("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.w("MainActivity", "Received connection message: " + message);
    }
}
