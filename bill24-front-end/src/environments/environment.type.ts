export type Environment = {
    production: boolean;
    baseAPI: {
        server: string;
        mapKey: string;
        googleAuthClientId: string;
        webSocketUrl: string;
    };
}