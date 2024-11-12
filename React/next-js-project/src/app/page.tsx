function MyButton({ title }: { title: string }) {
    return <button>{title}</button>;
}
export default function Home() {
    return (
        <>
            <h1>Welcome to my app</h1>
            <MyButton title="I'm a button of the page" />
        </>
    );
}
